package com.raspi.retail.backend.model.daos;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.query.store.ProductDAOQueryStore;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.DBDriverService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>
 *     DAO (Data Access Object) for doing CRUD Tasks for the
 *     <strong>Products</strong> Table.
 * </p>
 */
public class ProductDAO implements DAO {

    /**
     * Database Connection for this DAO
     */
    private final Connection dbct = DBDriverService.setupConnection();

    /**
     * ProductDAO Singleton object
     */
    private static ProductDAO pDAO = null;

    private static final ModelFactory pDTOFactory = ModelFactoryProducer.createFactory("dto");


    private ProductDAO() {}

    /**
     * Sets up a singleton ProductDAO
     * @return productDAO singleton (needs to typecast)
     */
    public static ProductDAO getDAOInstance() {
        if(pDAO == null) pDAO = new ProductDAO(); return pDAO;
    }

    // ========================================================================================== //

    /*
                                _                    _    _                 _
                               | |                  | |  | |               | |
         _ __   ___   __ _   __| |  _ __ ___    ___ | |_ | |__    ___    __| | ___
        | '__| / _ \ / _` | / _` | | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |/ __|
        | |   |  __/| (_| || (_| | | | | | | ||  __/| |_ | | | || (_) || (_| |\__ \
        |_|    \___| \__,_| \__,_| |_| |_| |_| \___| \__||_| |_| \___/  \__,_||___/
     */
    /**
     * Method to get all current products from the database, and return as an ArrayList.
     *
     * @return ArrayList of all products from database as ProductDTOs
     */
    public ArrayList<ProductDTO> getAllProducts() {

        ArrayList<ProductDTO> products = new ArrayList<>();
        ResultSet dbProducts;
        String setQuery = ProductDAOQueryStore.GET_ALL.getQuery();

        try {
            if (dbct != null) { // if true, connection successful
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);
                dbProducts = prepStmt.executeQuery();
                products = convertResultSetRecordsToArrayList(dbProducts);
            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return products;

    }

        /**
     * Method to get a row/rows of data into an ArrayList by getting its product name.
     *
     * @param nameQuery name of product one wishes to search
     * @return ArrayList of one or more products as a ProductDTO
     */
    public ArrayList<ProductDTO> getProductByName(String nameQuery) {

        ArrayList<ProductDTO> products = new ArrayList<>();
        ResultSet dbProducts = null;
        String setQuery = ProductDAOQueryStore.GET_ONE_BY_NAME.getQuery();

        try {
            if(dbct != null) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setString(1, ".*"+ nameQuery +".*");
                dbProducts = prepStmt.executeQuery();
                products = convertResultSetRecordsToArrayList(dbProducts);

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
        }

        return products;
    }

    /**
     * Method to get a row/rows of data into an ArrayList by getting its product type.
     *
     * @param typeQuery product type one wishes to search
     * @return ArrayList of one or more Products as a ProductDTO
     */
    public ArrayList<ProductDTO> getProductByType(String typeQuery) {

        ArrayList<ProductDTO> products = new ArrayList<>();
        ResultSet dbProducts = null;
        String setQuery = ProductDAOQueryStore.GET_ONE_BY_TYPE.getQuery();

        try {
            if(dbct != null) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setString(1, ".*"+ typeQuery +".*");
                dbProducts = prepStmt.executeQuery();
                products = convertResultSetRecordsToArrayList(dbProducts);

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
        }

        return products;
    }

    /**
     * Method to get a single db row and converted into a ProductDTO by getting its product id.
     *
     * @param id selected product ID
     * @return ProductDTO of a single product from specified product ID
     */
    public ProductDTO getProductById(int id) {

        ProductDTO foundProduct = (ProductDTO) pDTOFactory.createDTOPrototype("product");
        ResultSet dbProduct = null;
        String setQuery = ProductDAOQueryStore.GET_ONE_BY_ID.getQuery();

        try {
            if(dbct != null) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setInt(1, id);
                dbProduct = prepStmt.executeQuery();
                foundProduct = convertResultSetRecordToDTO(dbProduct);

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
            return null;
        }

        return foundProduct;

    }

    /**
     * Method to get a single db row into a ResultSet by getting its product id.
     *
     * Primarily used within this DAO for the delete functionality.
     *
     * @param id selected product ID
     * @return ResultSet of a single product with specified product ID
     */
    private ResultSet getProductResultSetById(int id) {

        ResultSet dbProduct = null;
        String setQuery = ProductDAOQueryStore.GET_ONE_BY_ID.getQuery();

        try {
            if(dbct != null) {
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                prepStmt.setInt(1, id);
                dbProduct = prepStmt.executeQuery();

            } else {
                System.err.println("Cannot connect to server. Please try again when the SQL server is running.");
            }
        } catch(SQLException sqlExc) {
            System.err.println("Something went wrong with retrieving data from DB.");
            System.err.println("["+sqlExc.getErrorCode()+"]: " + sqlExc.getMessage());
        }

        return dbProduct;
    }

    /*
                             _                      _   _               _
          ___ _ __ ___  __ _| |_ ___ _ __ ___   ___| |_| |__   ___   __| |___
         / __| '__/ _ \/ _` | __/ _ | '_ ` _ \ / _ | __| '_ \ / _ \ / _` / __|
        | (__| | |  __| (_| | ||  __| | | | | |  __| |_| | | | (_) | (_| \__ \
        \___|_|  \___|\__,_|\__\___|_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
     */

    /**
     * adds a product to the database.
     *
     * @param newProduct a ProductDTO with set values
     */
    public void addProduct(ProductDTO newProduct) {
        String setQuery = ProductDAOQueryStore.POST_ONE.getQuery();

        try {
            if(dbct != null) {

                dbct.setAutoCommit(false);
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                // set fields
                prepStmt.setString(1, newProduct.getName());
                prepStmt.setInt(2, newProduct.getStock());
                prepStmt.setDouble(3, newProduct.getPrice());
                prepStmt.setString(4, newProduct.getProductType());
                prepStmt.setString(5, newProduct.getDescription());
                prepStmt.setString(6, newProduct.getUrlListing());

                // execute statement and return successful operation
                prepStmt.executeUpdate();
                dbct.commit();

                System.out.println("Product Successfully Added!");

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
                         _       _                      _   _               _
         _   _ _ __   __| | __ _| |_ ___ _ __ ___   ___| |_| |__   ___   __| |___
        | | | | '_ \ / _` |/ _` | __/ _ | '_ ` _ \ / _ | __| '_ \ / _ \ / _` / __|
        | |_| | |_) | (_| | (_| | ||  __| | | | | |  __| |_| | | | (_) | (_| \__ \
        \__,_| .__/ \__,_|\__,_|\__\___|_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
             |_|
     */
        /**
     * updates a product with set parameters
     *
     * @param id selected product id to update
     * @param updatedProductData updated data from the old one
     */
    public void updateProduct(int id, ProductDTO updatedProductData) {

        String setQuery = ProductDAOQueryStore.PATCH_ONE.getQuery();

        // 1. Save previous values to a temporary ProductDTO
        ProductDTO oldProduct = getProductById(id);

        // 2. update values from the updated ProductDTO (while filling in empty ones using previous values)
            try {
            if(dbct != null) {

                dbct.setAutoCommit(false);
                PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                // set fields

                // set search parameter to that product id
                prepStmt.setInt(7, id);

                // update name field if not empty
                if(!updatedProductData.getName().equals("")) {
                    prepStmt.setString(1, updatedProductData.getName());
                } else {
                    prepStmt.setString(1, oldProduct.getName());
                }

                // update stock field if not empty
                if(updatedProductData.getStock() >= 0) {
                    prepStmt.setInt(2, updatedProductData.getStock());
                } else {
                    prepStmt.setInt(2, oldProduct.getStock());
                }

                // update price field if not empty
                if(updatedProductData.getPrice() >= 0.0) {
                    prepStmt.setDouble(3, updatedProductData.getPrice());
                } else {
                    prepStmt.setDouble(3, oldProduct.getPrice());
                }

                // update product type field if not empty
                if(!updatedProductData.getProductType().equals("")) {
                    prepStmt.setString(4, updatedProductData.getProductType());
                } else {
                    prepStmt.setString(4, oldProduct.getProductType());
                }

                // update product description field if not empty
                if(!updatedProductData.getDescription().equals("")) {
                    prepStmt.setString(5, updatedProductData.getDescription());
                } else {
                    prepStmt.setString(5, oldProduct.getDescription());
                }

                // update url if not empty
                if(!updatedProductData.getUrlListing().equals("")) {
                    prepStmt.setString(6, updatedProductData.getUrlListing());
                } else {
                    prepStmt.setString(6, oldProduct.getUrlListing());
                }

                // save to DB and return successful operation
                prepStmt.executeUpdate();
                dbct.commit();
                System.out.println("Product Updated!");

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
    /**
     * deletes a product with a product id
     *
     * @param id selected product id to delete
     */
    public void deleteProduct(int id) {
        ResultSet dbProduct = getProductResultSetById(id);
        String setQuery = ProductDAOQueryStore.DELETE_ONE.getQuery();

        try {
            if(dbct != null) {
                dbct.setAutoCommit(false);

                // check if product exists first
                if(dbProduct.next()) {
                    PreparedStatement prepStmt = dbct.prepareStatement(setQuery);

                    prepStmt.setInt(1, id);

                    // execute statement and return successful operation
                    prepStmt.executeUpdate();
                    dbct.commit();

                    System.out.println("Product Successfully Deleted!");

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
    public ArrayList<ProductDTO> convertResultSetRecordsToArrayList(ResultSet rs) {

        ArrayList<ProductDTO> products = new ArrayList<>();

        try {
            if(rs.next()) {
                do {
                    ProductDTO currProduct = (ProductDTO) pDTOFactory.createDTOPrototype("product");

                    currProduct.setId(rs.getInt("id"));
                    currProduct.setName(rs.getString("name"));
                    currProduct.setStock(rs.getInt("stock"));
                    currProduct.setPrice(rs.getDouble("price"));
                    currProduct.setProductType(rs.getString("type"));
                    currProduct.setDescription(rs.getString("description"));
                    currProduct.setUrlListing(rs.getString("url"));

                    products.add(currProduct);
                } while(rs.next());
            } else {
                System.out.println("No Products found.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong when converting ResultSet to ArrayList.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
        }

        return products;
    }


    @Override
    public ProductDTO convertResultSetRecordToDTO(ResultSet rs) {
        ProductDTO product = (ProductDTO) pDTOFactory.createDTOPrototype("product");
        try {
            if(rs.next()) {

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                product.setProductType(rs.getString("type"));
                product.setDescription(rs.getString("description"));
                product.setUrlListing(rs.getString("url"));

            } else {
                System.out.println("No Products Found.");
            }
        } catch (SQLException sqlExc) {
            System.err.println("Something went wrong when converting ResultSet to ProductDTO.");
            System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());

        }

        return product;

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // unused
    @Override
    public <E> ArrayList<E> convertResultSetRecordsToArrayList(CustomerType customerType, ResultSet rs) {
        return null;
    }
}
