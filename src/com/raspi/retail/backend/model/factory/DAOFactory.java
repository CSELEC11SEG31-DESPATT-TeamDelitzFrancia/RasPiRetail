package com.raspi.retail.backend.model.factory;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.daos.*;
import com.raspi.retail.backend.model.dtos.DTO;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory extends ModelFactory {

    private static final Map<String, DAO> prototypeStore = new HashMap<>();

    public DAOFactory() {
        prototypeStore.put("product", ProductDAO.getDAOInstance());
        prototypeStore.put("cart", CartDAO.getDAOInstance());
        prototypeStore.put("user", UserDAO.getDAOInstance());
        prototypeStore.put("package", PackageDAO.getDAOInstance());
    }

    /**
     * Creates a DAO Factory.
     *
     * @param type ("product"|"cart"|"user") what prototype of dao is preferred.
     * @return DAO of that prototype. (must be typecasted).
     */
    @Override
    public DAO createDAOPrototype(String type) {
        try {
            return (DAO) prototypeStore.get(type.toLowerCase()).clone();
        } catch (CloneNotSupportedException cnsExc) {
            System.err.println("Cannot create prototype.");
            System.err.println(cnsExc.getMessage());
            return null;
        }

    }


    // ========================================
    // UNUSED
    @Override
    public DTO createDTOPrototype(String type)  {
        return null;
    }
}
