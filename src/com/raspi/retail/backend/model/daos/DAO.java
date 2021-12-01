package com.raspi.retail.backend.model.daos;

import com.raspi.retail.backend.model.dtos.DTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Data Access Objects (DAOs)
 *
 * <p>
 *    These are objects (particularly singletons) that are used to manipulate basic
 *    CRUD (Create, Read, Update, Delete) methods for a specific database table.
 * </p>
 *
 * <p>
 *     These primarily handle accessing data to and from the database, and converts
 *     it to app-readable objects.
 * </p>
 */
public interface DAO extends Cloneable {

    // must always return a singleton instance
    static DAO getDAOInstance() {
        return null;
    }

    // must always have a conversion method t an Iterator
    Iterator convertResultSetRecordsToIterator(ResultSet rs);
    Iterator convertResultSetRecordsToIterator(CustomerType customerType, ResultSet rs);

    // must always have a conversion method to a single single DTO
    DTO convertResultSetRecordToDTO(ResultSet rs);

    // must be cloneable
    Object clone() throws CloneNotSupportedException;


}
