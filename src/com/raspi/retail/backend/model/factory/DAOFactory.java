package com.raspi.retail.backend.model.factory;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.daos.CartDAO;
import com.raspi.retail.backend.model.daos.DAO;
import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.daos.UserDAO;
import com.raspi.retail.backend.model.dtos.DTO;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory extends ModelFactory {

    private static final Map<String, DAO> prototypeStore = new HashMap<>();

    public DAOFactory() {
        prototypeStore.put("product", ProductDAO.getDAOInstance());
        prototypeStore.put("cart", CartDAO.getDAOInstance());
        prototypeStore.put("user", UserDAO.getDAOInstance());
    }

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
