package com.raspi.retail.backend.model.daos;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.query.store.CartDAOCustomerQueryStore;
import com.raspi.retail.backend.model.daos.query.store.CartDAOGuestQueryStore;
import com.raspi.retail.backend.model.daos.query.store.CartDAOQueryStore;
import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.DTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.DBDriverService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO implements DAO {

     /**
     * Database Connection for this DAO
     */
    private final Connection dbct = DBDriverService.setupConnection();

    /**
     * ProductDAO Singleton object
     */
    private static CartDAO cDAO = null;

    private static final ModelFactory cDTOFactory = ModelFactoryProducer.createFactory("dto");

    private CartDAO() {}

    /**
     * Sets up a singleton CartDAO
     * @return cartDAO singleton (needs to typecast)
     */
    public static CartDAO getDAOInstance() {
        if(cDAO == null) cDAO = new CartDAO(); return cDAO;
    }


    /**
     * Sets appropriate query based on customerType and queryType
     *
     * @param customerType sets what type of customer this query will handle
     * @param queryType sets what type of query (either Create, Read, Update, or Delete) it will handle
     * @return a set query that will correspond to the parameters
     */
    private String queryBuilder(CustomerType customerType, CartDAOQueryStore queryType) {
        String setQuery = "";
        if(customerType == CustomerType.CUSTOMER) {
            setQuery = CartDAOCustomerQueryStore.valueOf(queryType.getQueryType()).getQuery();
        }
        else if(customerType == CustomerType.GUEST) {
            setQuery = CartDAOGuestQueryStore.valueOf(queryType.getQueryType()).getQuery();
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
     * Method to get a single customer's cart from the database.
     *
     * <p>
     *      This uses what's called a database 'view' as apart from a database table. A database
     *      view is basically a virtual table that uses existing database tables for values, and
     *      uses a single set query to store the data in the view.
     * </p>
     *
     * @param userID the customer id you wish to get a cart of.
     * @param customerType an enum of what type of customer you want to get the cart from.
     * @return a customer's cart, which is basically an ArrayList of CartItems
     */
    public ArrayList<CartItem> getUserCart(int userID, CustomerType customerType) {

        ArrayList<CartItem> cart = new ArrayList<>();
        ResultSet dbCart;

        // set appropriate query based on customerType
        String setQuery = queryBuilder(customerType, CartDAOQueryStore.GET_USER_CART);

        try {
            if(dbct != null && !setQuery.equals("")) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                prepStmt.setInt(1, userID);
                dbCart = prepStmt.executeQuery();
                cart = convertResultSetRecordsToArrayList(dbCart);
            } else if(setQuery.equals("")) {
                  System.err.println("You didn't set a customer type. Please try again.");
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return cart;

    }

    /**
     * Method to get a single customer cart item from the customer's cart from the database.
     *
     * @param userID the customer id to get the cart from.
     * @param productID the product id to get from the customer's cart.
     * @param customerType what type of customer is the customer
     * @return ResultSet containing at least one query where the product item contains from inside a customer's cart.
     */
    private ResultSet getUserCartItemResultSetById(int userID, int productID, CustomerType customerType) {

        ResultSet dbCartItem = null;
        String setQuery = queryBuilder(customerType, CartDAOQueryStore.GET_USER_CART_ITEM);

        try {
            if(dbct != null) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setInt(1, userID);
                prepStmt.setInt(2, productID);

                dbCartItem = prepStmt.executeQuery();

             } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
        }

        return dbCartItem;
    }

    /*
                             _                      _   _               _
          ___ _ __ ___  __ _| |_ ___ _ __ ___   ___| |_| |__   ___   __| |___
         / __| '__/ _ \/ _` | __/ _ | '_ ` _ \ / _ | __| '_ \ / _ \ / _` / __|
        | (__| | |  __| (_| | ||  __| | | | | |  __| |_| | | | (_) | (_| \__ \
        \___|_|  \___|\__,_|\__\___|_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
    */

    /**
     * Method to add to cart by using standard parameters.
     *
     * @param userID user id from which customer is requesting adding to cart.
     * @param productID product id from which item is to be added to the cart.
     * @param quantity how much of this product the customer is requesting.
     * @param customerType what type of customer is the customer.
     */
    public void addToCart(int userID, int productID, int quantity, CustomerType customerType) {

        // set appropriate query based on customerType
        String setQuery = queryBuilder(customerType, CartDAOQueryStore.POST_USER_CART_ITEM);

        try {

            if (dbct != null) {

                dbct.setAutoCommit(false);
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                // set fields
                prepStmt.setInt(1, userID);
                prepStmt.setInt(2, productID);
                prepStmt.setInt(3, quantity);

                // execute statement and return successful operation.
                prepStmt.executeUpdate();
                dbct.commit();

                System.out.println("Item Successfully added to cart!");
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

    /*
                         _       _                      _   _               _
         _   _ _ __   __| | __ _| |_ ___ _ __ ___   ___| |_| |__   ___   __| |___
        | | | | '_ \ / _` |/ _` | __/ _ | '_ ` _ \ / _ | __| '_ \ / _ \ / _` / __|
        | |_| | |_) | (_| | (_| | ||  __| | | | | |  __| |_| | | | (_) | (_| \__ \
        \__,_| .__/ \__,_|\__,_|\__\___|_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
             |_|
     */

    /**
     * Method to update cart by using standard parameters.
     *
     * @param userID user id from which customer is requesting updating quantity to cart.
     * @param productID product id from which item is to be updated to the cart.
     * @param quantity how much of this product the customer is requesting.
     * @param customerType what type of customer is the customer.
     */

    public void updateCartItemQuantity(int userID, int productID, int quantity, CustomerType customerType) {

        String setQuery = queryBuilder(customerType, CartDAOQueryStore.PUT_USER_CART_ITEM);

        try {
            if(dbct != null) {
                dbct.setAutoCommit(false);

                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setInt(1, quantity);
                prepStmt.setInt(2, userID);
                prepStmt.setInt(3, productID);

                // execute statement and return successful operation.
                prepStmt.executeUpdate();
                dbct.commit();

                System.out.println("Cart Item successfully updated!");
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

    /*
             _        _        _                            _    _                 _
            | |      | |      | |                          | |  | |               | |
          __| |  ___ | |  ___ | |_   ___   _ __ ___    ___ | |_ | |__    ___    __| | ___
         / _` | / _ \| | / _ \| __| / _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |/ __|
        | (_| ||  __/| ||  __/| |_ |  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |\__ \
        \__,_| \___||_| \___| \__| \___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_||___/
     */

    /**
     * removes an item from the user's cart.
     *
     * @param userID user id from which user wants to delete an item in their cart.
     * @param productID product id from which item is going to be removed from the cart.
     * @param customerType what type of customer is the customer.
     */
    public void removeFromCart(int userID, int productID, CustomerType customerType) {

        String setQuery = queryBuilder(customerType, CartDAOQueryStore.DELETE_USER_CART_ITEM);
        ResultSet dbCartItem = getUserCartItemResultSetById(userID, productID, customerType);

        try {
            if(dbct != null) {
                dbct.setAutoCommit(false);

                if(dbCartItem.next()) {
                    PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                    prepStmt.setInt(1, userID);
                    prepStmt.setInt(2, productID);

                    // execute statement and return successful operation
                    prepStmt.executeUpdate();
                    dbct.commit();

                    System.out.println("Cart Item Successfully Deleted!");

                } else {
                    dbct.rollback();
                    System.out.println("Product doesn't exist.");
                }

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

    // ========================================================================================== //
    @Override
    public ArrayList<CartItem> convertResultSetRecordsToArrayList(ResultSet rs) {
        ArrayList<CartItem> cart = new ArrayList<>();

        try {
            if(rs.next()) {
                do {
                    CartItem currItem = new CartItem();

                    currItem.setProductID(rs.getInt("productID"));
                    currItem.setProductName(rs.getString("productName"));
                    currItem.setQuantity(rs.getInt("quantity"));
                    currItem.setSubtotal(rs.getDouble("subtotal"));

                    cart.add(currItem);
                } while (rs.next());
            } else {
                System.out.println("Cart is empty.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong when converting ResultSet to ArrayList.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return cart;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // ========================================================================================== //
    // UNUSED
    @Override
    public DTO convertResultSetRecordToDTO(ResultSet rs) {
        return null;
    }
    @Override
    public <E> ArrayList<E> convertResultSetRecordsToArrayList(CustomerType customerType, ResultSet rs) {
        return null;
    }
}
