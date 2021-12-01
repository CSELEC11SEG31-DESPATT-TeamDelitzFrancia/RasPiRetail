package com.raspi.retail.backend.model.daos;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.query.store.PackageDAOCustomerQueryStore;
import com.raspi.retail.backend.model.daos.query.store.PackageDAOGuestQueryStore;
import com.raspi.retail.backend.model.daos.query.store.PackageDAOQueryStore;
import com.raspi.retail.backend.model.dtos.DTO;
import com.raspi.retail.backend.model.dtos.PackageDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.model.dtos.enums.PackageType;
import com.raspi.retail.backend.util.DBDriverService;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class PackageDAO implements DAO {

    /**
     * Database Connection for this DAO
     */
    private final Connection dbct = DBDriverService.setupConnection();

    /**
     * PackageDAO Singleton Object
     */
    private static PackageDAO pkDAO = null;

    private static final ModelFactory pkDTOFactory = ModelFactoryProducer.createFactory("dto");

    private PackageDAO() {}

    /**
     * Sets up a singleton PackageDAO
     * @return packageDAO singleton (needs to typecast)
     */
    public static PackageDAO getDAOInstance() {
        if(pkDAO == null) pkDAO = new PackageDAO(); return pkDAO;
    }

    private String queryBuilder(CustomerType customerType, PackageDAOQueryStore queryType) {
        String setQuery = "";
        if(customerType == CustomerType.CUSTOMER) {
            setQuery = PackageDAOCustomerQueryStore.valueOf(queryType.getQueryType()).getQuery();
        } else if(customerType == CustomerType.GUEST) {
            setQuery = PackageDAOGuestQueryStore.valueOf(queryType.getQueryType()).getQuery();
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
     * Retrieves all current packages waiting for delivery.
     *
     * @param userType (CUSTOMER, GUEST, ADMIN) what type of user are you retrieving
     * @return ArrayList of packages from either all GUESTs or CUSTOMERs
     */
    public Iterator<PackageDTO> getPackages(CustomerType userType) {

        Iterator<PackageDTO> packages = null;
        ResultSet dbPackages;

        // set appropriate query based on customerType
        String setQuery = queryBuilder(userType, PackageDAOQueryStore.GET_ALL_PACKAGES);

        try {
            if(dbct != null && !setQuery.equals("")) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                dbPackages = prepStmt.executeQuery();
                packages = convertResultSetRecordsToIterator(userType, dbPackages);
            } else if(setQuery.equals("")) {
                  System.err.println("You didn't set a customer type. Please try again.");
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return packages;
    }


    /**
     * method to get a single user by id and returns a ResultSet instead of a DAO.
     *
     * @param userType what type of user will it retrieve
     * @param packageID package id to be selected
     * @return ResultSet of that package
     */
    private ResultSet getPackageResultSetByID(CustomerType userType, int packageID) {

        String setQuery = queryBuilder(userType, PackageDAOQueryStore.GET_PACKAGE_BY_ID);

        try {
            if(dbct != null && !setQuery.equals("")) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                prepStmt.setInt(1, packageID);
                return prepStmt.executeQuery();
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }

        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return null;

    }

    /*
                             _                      _   _               _
          ___ _ __ ___  __ _| |_ ___ _ __ ___   ___| |_| |__   ___   __| |___
         / __| '__/ _ \/ _` | __/ _ | '_ ` _ \ / _ | __| '_ \ / _ \ / _` / __|
        | (__| | |  __| (_| | ||  __| | | | | |  __| |_| | | | (_) | (_| \__ \
        \___|_|  \___|\__,_|\__\___|_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
     */


    /**
     * adds a new checkout package to the database.
     *
     * @param userType type of user to checkout a package
     * @param pkgType what packaging does it require
     * @param customerID customer id to reference the cart item/s for the package
     */
    public void addPackage(CustomerType userType, PackageType pkgType, int customerID) {
        String setQuery = queryBuilder(userType, PackageDAOQueryStore.POST_PACKAGE);

        try {
            if(dbct != null) {
                dbct.setAutoCommit(false);
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setString(1, pkgType.toString());
                prepStmt.setInt(2, customerID);

                prepStmt.executeUpdate();
                dbct.commit();
                System.out.println("Package Successfully Added!");

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            try {
                dbct.rollback();
                System.err.println("Something went wrong when adding file to database.");
                System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
            } catch(SQLException rbExc) {
                System.err.println("Something went wrong when rolling back changes.");
                System.err.println("["+rbExc.getErrorCode()+"]: " + rbExc.getMessage());
            }
        }

    }

    /*
             _        _        _                            _    _                 _
            | |      | |      | |                          | |  | |               | |
          __| |  ___ | |  ___ | |_   ___   _ __ ___    ___ | |_ | |__    ___    __| | ___
         / _` | / _ \| | / _ \| __| / _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |/ __|
        | (_| ||  __/| ||  __/| |_ |  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |\__ \
        \__,_| \___||_| \___| \__| \___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_||___/
     */

    public void removePackage(CustomerType userType, int pkgID) {

        ResultSet dbPkg = getPackageResultSetByID(userType, pkgID);
        String setQuery = queryBuilder(userType, PackageDAOQueryStore.DELETE_PACKAGE);

        try {
            if(dbct != null) {

                dbct.setAutoCommit(false);

                if(dbPkg.next()) {
                    PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                    prepStmt.setInt(1, pkgID);

                    prepStmt.executeUpdate();
                    dbct.commit();
                    System.out.println("Package Successfully Deleted!");
                } else {
                    dbct.rollback();
                    System.out.println("Package doesn't exist.");
                }

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch (SQLException sqlExc) {
            try {
                dbct.rollback();
                System.err.println("Something went wrong when adding file to database.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            } catch (SQLException rbExc) {
                System.err.println("Something went wrong when rolling back changes.");
                System.err.println("[" + rbExc.getErrorCode() + "]: " + rbExc.getMessage());
            }
        }

    }

    @Override
    public Iterator<PackageDTO> convertResultSetRecordsToIterator(CustomerType customerType, ResultSet rs) {
        String customerID = customerType.equals(CustomerType.CUSTOMER)
                ? "customerID"
                : customerType.equals(CustomerType.GUEST)
                    ? "guestID"
                    : null;
        ArrayList<PackageDTO> packages = new ArrayList<>();

        try {
            if(rs.next()) {
                do {
                    PackageDTO.Builder pkgBuilder = PackageDTO.Builder.newInstance();
                    PackageDTO pkg;

                    pkg = pkgBuilder
                            .setPackageID(rs.getInt("packageID"))
                            .setCustomerID(rs.getInt(customerID))
                            .setCustomerType(customerType)
                            .setPackageType(PackageType.valueOf(rs.getString("packageType")))
                            .setTotalQty(rs.getInt("totalQty"))
                            .setGrandTotal(rs.getDouble("grandTotal"))
                            .pack();

                    packages.add(pkg);

                } while (rs.next());
            } else {
                    System.out.println("There are no Registered Customers.");
            }
        } catch (SQLException sqlExc) {
                System.err.println("Something went wrong when converting ResultSet to ArrayList.");
                System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
        }

        return packages.iterator();

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    // unused
    @Override
    public DTO convertResultSetRecordToDTO(ResultSet rs) {
        return null;
    }

    @Override
    public Iterator convertResultSetRecordsToIterator(ResultSet rs) {
        return null;
    }
}
