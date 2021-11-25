package com.raspi.retail.backend.model;

import com.raspi.retail.backend.model.daos.DAO;
import com.raspi.retail.backend.model.dtos.DTO;

public abstract class ModelFactory {

    public abstract DAO createDAOPrototype(String type);
    public abstract DTO createDTOPrototype(String type);

}
