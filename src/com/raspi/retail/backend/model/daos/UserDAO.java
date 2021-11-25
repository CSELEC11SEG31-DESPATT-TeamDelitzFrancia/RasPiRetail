package com.raspi.retail.backend.model.daos;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.query.store.UserDAOAdminQueryStore;
import com.raspi.retail.backend.model.daos.query.store.UserDAOCustomerQueryStore;
import com.raspi.retail.backend.model.daos.query.store.UserDAOGuestQueryStore;
import com.raspi.retail.backend.model.daos.query.store.UserDAOQueryStore;
import com.raspi.retail.backend.model.dtos.*;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.DBDriverService;
import com.raspi.retail.backend.util.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements DAO {

    /**
     * Database Connection for this DAO
     */
    private final Connection dbct = DBDriverService.setupConnection();

    /**
     * ProductDAO Singleton object
     */
    private static UserDAO uDAO = null;

    private static final ModelFactory uDTOFactory = ModelFactoryProducer.createFactory("dto");

    private UserDAO() {}

    /**
     * Sets up a singleton UserDAO
     * @return userDAO singleton (needs to typecast)
     */
    public static UserDAO getDAOInstance() {
        if(uDAO == null) uDAO = new UserDAO(); return uDAO;
    }

    /**
     * Sets appropriate query based on customerType and queryType
     *
     * @param customerType sets what type of customer this query will handle
     * @param queryType sets what type of query (either Create, Read, Update, or Delete) it will handle
     * @return a set query that will correspond to the parameters
     */
    private String queryBuilder(CustomerType customerType, UserDAOQueryStore queryType) {
        String setQuery = "";
        if(customerType == CustomerType.CUSTOMER) {
            setQuery = UserDAOCustomerQueryStore.valueOf(queryType.getQueryType()).getQuery();
        } else if(customerType == CustomerType.GUEST) {
            setQuery = UserDAOGuestQueryStore.valueOf(queryType.getQueryType()).getQuery();
        } else if(customerType == CustomerType.ADMIN) {
            setQuery = UserDAOAdminQueryStore.valueOf(queryType.getQueryType()).getQuery();
        }

        return setQuery;
    }

    // ========================================================================================== //

    /*
                                _                    _    _                 _
                               | |                  | |  | |               | |
         _ __   ___   __ _   __| |   _ __ ___    ___ | |_ | |__    ___    __| | ___
        | '__| / _ \ / _` | / _` |  | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |/ __|
        | |   |  __/| (_| || (_| |  | | | | | ||  __/| |_ | | | || (_) || (_| |\__ \
        |_|    \___| \__,_| \__,_|  |_| |_| |_| \___| \__||_| |_| \___/  \__,_||___/
     */

    /**
     * Retrieves a set of all users that can either be registered customers (members),
     * guests, or administrators.
     *
     * @param userType (CUSTOMER, GUEST, ADMIN) what type of user you are retrieving
     * @param <DTOType> DTO type based on what user type you are retrieving
     * @return ArrayList based on what user type you are retrieving
     */
    public <DTOType extends DTO> ArrayList<DTOType> getAllUsers(CustomerType userType) {


        ArrayList<DTOType> users = new ArrayList<>();
        ResultSet dbUsers;

        // set appropriate query based on customerType
        String setQuery = queryBuilder(userType, UserDAOQueryStore.GET_ALL_USERS);

        try {
            if(dbct != null && !setQuery.equals("")) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                dbUsers = prepStmt.executeQuery();
                users = convertResultSetRecordsToArrayList(userType, dbUsers);
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

       return users;

    }

    public UserDTO getOneUser(CustomerType userType, int id) {

        UserDTO user = null;
        ResultSet dbUser;

        String setQuery = queryBuilder(userType, UserDAOQueryStore.GET_USER_BY_ID);

        try {

            if(dbct != null && !setQuery.equals("")) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                prepStmt.setInt(1, id);
                dbUser = prepStmt.executeQuery();
                user = convertResultSetRecordToDTO(userType, dbUser);
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }

        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return user;
    }

    @Override
    public <E> ArrayList<E> convertResultSetRecordsToArrayList(CustomerType customerType, ResultSet rs) {

        if(customerType == CustomerType.CUSTOMER) {
            ArrayList<MemberDTO> customers = new ArrayList<>();

            try {
                if(rs.next()) {
                    do {
                        MemberDTO currCustomer = (MemberDTO) uDTOFactory.createDTOPrototype("customer");

                        currCustomer.setId(rs.getInt("id"));
                        currCustomer.setUsername(rs.getString("username"));
                        currCustomer.setPassword(Security.decrypt(rs.getString("password")));
                        currCustomer.setEmail(rs.getString("email"));
                        currCustomer.setFirstName(rs.getString("firstName"));
                        currCustomer.setLastName(rs.getString("lastName"));
                        currCustomer.setAddressLine1(rs.getString("addressLine1"));
                        currCustomer.setAddressLine2(rs.getString("addressLine2"));
                        currCustomer.setCcNo(new CreditCard(rs.getString("ccNo")));

                        customers.add(currCustomer);
                    } while (rs.next());
                } else {
                    System.out.println("There are no Registered Customers.");
                }
            } catch (SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
            }

            return (ArrayList<E>) customers;

        }
        else if (customerType == CustomerType.GUEST) {
            ArrayList<GuestDTO> guests = new ArrayList<>();

            try {

                if(rs.next()) {
                    do {
                        GuestDTO currGuest = (GuestDTO) uDTOFactory.createDTOPrototype("guest");

                        currGuest.setId(rs.getInt("id"));
                        currGuest.setEmail(rs.getString("email"));
                        currGuest.setFirstName(rs.getString("firstName"));
                        currGuest.setLastName(rs.getString("lastName"));
                        currGuest.setAddressLine1(rs.getString("addressLine1"));
                        currGuest.setAddressLine2(rs.getString("addressLine2"));
                        currGuest.setCcNo(new CreditCard(rs.getString("ccNo")));

                        guests.add(currGuest);

                    } while(rs.next());
                } else {
                System.out.println("There are currently no Guest Accounts.");
                }
            } catch (SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            }

            return (ArrayList<E>) guests;

        }
        else if (customerType == CustomerType.ADMIN) {
            ArrayList<AdminDTO> admins = new ArrayList<>();

            try {
                if(rs.next()) {
                    do {
                        AdminDTO currAdmin = (AdminDTO) uDTOFactory.createDTOPrototype("admin");

                        currAdmin.setId(rs.getInt("id"));
                        currAdmin.setUsername(rs.getString("username"));
                        currAdmin.setPassword(Security.decrypt(rs.getString("password")));

                        admins.add(currAdmin);
                    } while(rs.next());
                } else {
                    System.out.println("There are currently no Admins. It is recommended to add one.");
                }
            } catch(SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            }

            return (ArrayList<E>) admins;

        }

        return null;
    }




    public UserDTO convertResultSetRecordToDTO(CustomerType userType, ResultSet rs) {
        if(userType == CustomerType.CUSTOMER) {
            MemberDTO currCustomer = (MemberDTO) uDTOFactory.createDTOPrototype("customer");

            try {
                if(rs.next()) {
                        currCustomer.setId(rs.getInt("id"));
                        currCustomer.setUsername(rs.getString("username"));
                        currCustomer.setPassword(Security.decrypt(rs.getString("password")));
                        currCustomer.setEmail(rs.getString("email"));
                        currCustomer.setFirstName(rs.getString("firstName"));
                        currCustomer.setLastName(rs.getString("lastName"));
                        currCustomer.setAddressLine1(rs.getString("addressLine1"));
                        currCustomer.setAddressLine2(rs.getString("addressLine2"));
                        currCustomer.setCcNo(new CreditCard(rs.getString("ccNo")));
                } else {
                    System.out.println("There are no Registered Customers.");
                }
            } catch (SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
            }

            return currCustomer;
        }
        else if(userType == CustomerType.GUEST) {
            GuestDTO currGuest = (GuestDTO) uDTOFactory.createDTOPrototype("guest");

            try {
                if(rs.next()) {

                    currGuest.setId(rs.getInt("id"));
                    currGuest.setEmail(rs.getString("email"));
                    currGuest.setFirstName(rs.getString("firstName"));
                    currGuest.setLastName(rs.getString("lastName"));
                    currGuest.setAddressLine1(rs.getString("addressLine1"));
                    currGuest.setAddressLine2(rs.getString("addressLine2"));
                    currGuest.setCcNo(new CreditCard(rs.getString("ccNo")));
                } else {
                System.out.println("There are currently no Guest Accounts.");
                }
            } catch (SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            }

            return currGuest;

        }
        else if(userType == CustomerType.ADMIN) {
            AdminDTO currAdmin = (AdminDTO) uDTOFactory.createDTOPrototype("admin");

            try {
                if(rs.next()) {

                        currAdmin.setId(rs.getInt("id"));
                        currAdmin.setUsername(rs.getString("username"));
                        currAdmin.setPassword(Security.decrypt(rs.getString("password")));

                } else {
                    System.out.println("There are currently no Admins. It is recommended to add one.");
                }
            } catch(SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            }

            return currAdmin;

        }

        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



    // unused
    @Override
    public <E> ArrayList<E> convertResultSetRecordsToArrayList(ResultSet rs) {
        return null;
    }

    @Override
    public DTO convertResultSetRecordToDTO(ResultSet rs) {
        return null;
    }
}
