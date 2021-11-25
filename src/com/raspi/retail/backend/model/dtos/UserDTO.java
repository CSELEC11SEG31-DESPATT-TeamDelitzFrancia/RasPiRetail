package com.raspi.retail.backend.model.dtos;

public class UserDTO implements DTO{

    protected String username;
    protected String password;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
