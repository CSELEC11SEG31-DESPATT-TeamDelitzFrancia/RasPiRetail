package com.raspi.retail.backend.util;

import com.raspi.retail.backend.config.DBConfig;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Methods that handle setting up a database connection and initialising a pre-defined SQL database using
 * an SQL file dump.
 */
public class DBDriverService implements DBConfig {

    /**
     * Singleton Connector
     */
    private static Connection dbct = null;

    // private constructor
    private DBDriverService() {}

    /**
     * Gets a database connection from set init values
     *
     * @return connection from DBConfig
     */
    private static Connection getDBConnection() {
        Connection ct = null;

        try {
            // setup JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // set DB Connection with DBConfig Credentials
            ct = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(SQLException sqlExc) {
            if(sqlExc.getErrorCode() == 1044) {
                System.err.println("SQL Permission Error. Please check if you have proper rights "
                    + "to the database you're connecting to.");
            } else {
                System.err.println("There's something wrong with connecting to the Database.");
                System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
            }
        } catch (ClassNotFoundException cnfExc) {
            System.err.println(cnfExc.getMessage());
        }

        return ct;
    }

    /**
     * Initialises a singleton database connection to be used app-wide.
     *
     * @return dbct - returns an already existing connection or sets a new one if it has not yet been initialised.
     */
    public static Connection setupConnection() {
        return ((dbct != null) ? dbct : getDBConnection());
    }

    /**
     * Initialises the Database from an existing SQL dump file.
     *
     * <p>Status Codes may either be the following: </p>
     *
     * <dl>
     *     <dt>0</dt>
     *     <dd>Database Initialisation Successfully Initialised</dd>
     *     <dt>1</dt>
     *     <dd>Database Previously Initialised, and would therefore skip initialisation</dd>
     *     <dt>-1</dt>
     *     <dd>Database failed to initialise</dd>
     * </dl>
     *
     * @return status code
     */
    private static int setupSqlDatabase() {
        dbct = setupConnection();
        StringBuilder commandStream = new StringBuilder();
        File sqlFile = new File(SQL_FILE);
        int statusCode = -1;

        /*
         * save all commands from SQL dump to <code>commandStream</code>
         */
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
            String line;
            line = reader.readLine();

            // save each line from sql dump to `commandStream`
            while (line != null) {
                commandStream.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException fnfExc) {
            System.err.println("Selected file not found.");
            System.err.println(fnfExc.getMessage());
        } catch (IOException ioExc) {
            System.err.println(ioExc.getMessage());
        }

        // convert `commandStream` to array; split by ';'
        String[] commandsArray = commandStream.toString().split(";");

        /*
         * execute all commands from `commandsArray`
         */
        try {
            Statement stmt = dbct.createStatement();
            dbct.setAutoCommit(false);

            // execute each line
            for(String cmd : commandsArray) {
                stmt.execute(cmd);
                dbct.commit();
            }

            // close connection
            dbct.close();

        } catch (SQLException sqlExc) {

            // rollback if one command in sql file fails
            try {
                dbct.rollback();
            } catch(SQLException rollSqlExc) {

                System.err.println("Something went wrong when rolling back SQL command.");
                System.err.println("[" + rollSqlExc.getErrorCode() + "]: " + rollSqlExc.getMessage());
            }

            // error code conversion to status code
            if(sqlExc.getErrorCode() == 1065) { // 1065 -> query was empty: Successfully Initialised
                statusCode = 0;
            }
            else if (sqlExc.getErrorCode() == 1050) { // 1050 -> table exists: Previously Initialised
                statusCode = 1;
            }
            else {
                statusCode = sqlExc.getErrorCode();
            }

        }

        // return final status code
        return statusCode;

    }

    /**
     * DBConfig Facade method to handle database initialisation with appropriate user-friendly response.
     */
    public static void buildSqlDatabase() {
        // loading message
        System.out.println("Initialising SQL Tables, please wait...");

        // set status code to an int
        int isSuccessfulInit = setupSqlDatabase();

        // returns appropriate user message
        if(isSuccessfulInit == 0) {
            System.out.println("SQL Tables successfully initialised!");
        } else if(isSuccessfulInit == 1) {
            System.out.println("SQL Tables Exist! Skipping SQL Initialisation...");
        } else {
            System.out.println("Looks like something went wrong when initialising the SQL Tables...");
            System.out.println("Error Code " + isSuccessfulInit);
        }

    }


    /**
     * Drops all SQL Tables (can be used to reset tables).
     *
     * <p>By default, this will give out a user prompt to ensure if the user really wants to drop all tables.</p>
     */
    public static void dropSqlDatabase() {
        dbct = setupConnection();
        String confirmation;
        String dropQuery = "DROP TABLE `RasPiRetail_CustomerCarts`, `RasPiRetail_Products`, `RasPiRetail_Users`;";

        confirmation = KBInput.readString("Are you sure you want to drop all tables? " +
                "\nTHIS CANNOT BE UNDONE (Y/n): ");

        if(confirmation.equalsIgnoreCase("y")) {
            try {
                Statement stmt = dbct.createStatement();
                dbct.setAutoCommit(false);

                // execute each line
                stmt.execute(dropQuery);
                dbct.commit();

                // close connection
                dbct.close();
            } catch(SQLException sqlExc) {

                // rollback if one command in sql file fails
                try {
                    System.err.println("Something went wrong when dropping SQL Tables.");
                    System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
                    dbct.rollback();
                } catch(SQLException rollSqlExc) {
                    System.err.println("Something went wrong when rolling back SQL command.");
                    System.err.println("[" + rollSqlExc.getErrorCode() + "]: " + rollSqlExc.getMessage());
                }

            }
        } else {
            System.out.println("Dropping of Database Tables Cancelled.");
        }

    }

    /**
     * Drops all SQL Tables (can be used to reset tables)
     *
     * <p>By default, this will give out a user prompt to ensure if the user really wants to drop all tables.</p>
     *
     * @param skipConfirmation Skips user prompt if true (must only be used if necessary,
     *                         and is <strong>NOT RECOMMENDED</strong> being used for user prompts.)
     */
    public static void dropSqlDatabase(boolean skipConfirmation) {
        dbct = setupConnection();
        String confirmation;
        String dropQuery = "DROP TABLE `RasPiRetail_CustomerCarts`, `RasPiRetail_Products`, `RasPiRetail_Users`;";

        confirmation = KBInput.readString("Are you sure you want to drop all tables? " +
                "\nTHIS CANNOT BE UNDONE (Y/n): ");

        if(confirmation.equalsIgnoreCase("y") || skipConfirmation) {
            try {
                Statement stmt = dbct.createStatement();
                dbct.setAutoCommit(false);

                // execute each line
                stmt.execute(dropQuery);
                dbct.commit();

                // close connection
                dbct.close();
            } catch(SQLException sqlExc) {

                // rollback if one command in sql file fails
                try {
                    System.err.println("Something went wrong when dropping SQL Tables.");
                    System.err.println("[" + sqlExc.getErrorCode() + "]: " + sqlExc.getMessage());
                    dbct.rollback();
                } catch(SQLException rollSqlExc) {
                    System.err.println("Something went wrong when rolling back SQL command.");
                    System.err.println("[" + rollSqlExc.getErrorCode() + "]: " + rollSqlExc.getMessage());
                }

            }
        } else {
            System.out.println("Dropping of Database Tables Cancelled.");
        }

    }

}
