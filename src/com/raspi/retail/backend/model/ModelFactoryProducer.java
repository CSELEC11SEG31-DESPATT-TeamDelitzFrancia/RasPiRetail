package com.raspi.retail.backend.model;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.factory.DAOFactory;
import com.raspi.retail.backend.model.factory.DTOFactory;

/**
 * Produces factories for DAOs and DTOs
 */
public class ModelFactoryProducer {

    /**
     * Creates either a Data Access Object factory, or a Data Transfer Object factory.
     * @param factoryType (DAO|DTO) -- creates a specific factory for each.
     * @return DAO/DTO Factory
     */
    public static ModelFactory createFactory(String factoryType) {
        ModelFactory factory = null;

        switch (factoryType.toLowerCase()) {
            case "dao":
                factory = new DAOFactory();
                break;
            case "dto":
                factory = new DTOFactory();
                break;
        }

        return factory;
    }

}
