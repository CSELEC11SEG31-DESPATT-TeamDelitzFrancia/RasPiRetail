package com.raspi.retail.backend.model.factory;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.daos.DAO;
import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.DTO;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;

import java.util.HashMap;
import java.util.Map;

public class DTOFactory extends ModelFactory {

    private static final Map<String, DTO> prototypeStore = new HashMap<>();

    public DTOFactory() {
        prototypeStore.put("product", new ProductDTO());
        prototypeStore.put("admin", new AdminDTO());
        prototypeStore.put("customer", new MemberDTO());
        prototypeStore.put("guest", new GuestDTO());
    }

    /**
     * Creates a DTO Factory.
     *
     * @param type ("product"|"admin"|"customer"|"guest") what prototype of dto is preferred.
     * @return DTO of that prototype. (Must be typecasted)
     */
    @Override
    public DTO createDTOPrototype(String type) {
        try {
            return (DTO) prototypeStore.get(type).clone();
        } catch (CloneNotSupportedException cnsExc) {
            System.err.println("Cannot create prototype.");
            System.err.println(cnsExc.getMessage());
            return null;
        }
    }


    // ========================================
    // UNUSED
    @Override
    public DAO createDAOPrototype(String type)  {
        return null;
    }
}
