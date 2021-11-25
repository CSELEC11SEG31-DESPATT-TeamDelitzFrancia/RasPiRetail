package com.raspi.retail.backend.config;

/**
 * Database configuration as an interface
 */
public interface DBConfig {

    /**
     * SQL File for Initialising Database Tables
     */
    String SQL_FILE = "./RasPiRetailDB.sql";

    /**
     * XAMPP Production Credentials
     *
     * <p>
     *     Uncomment these to use XAMPP configuration
     *     credentials.
     * </p>
     */
    // String DB_URL = "jdbc:mysql://localhost:3306/despatt";
    // String DB_USER = "root";
    // String DB_PASSWORD = "";

    /**
     * MariaDB+PhpMyAdmin Docker Dev Credentials
     *
     * <p>
     *     Comment these out if ever it will be used for
     *     submitting.
     * </p>
     * <p>
     *     These credentials require docker and docker-compose
     *     to run the MariaDB+PhpMyAdmin docker stack.
     * </p>
     */
    String DB_URL = "jdbc:mysql://localhost:12000/despatt";
    String DB_USER = "root";
    String DB_PASSWORD = "asdfasdf";

}
