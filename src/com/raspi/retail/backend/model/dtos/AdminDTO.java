package com.raspi.retail.backend.model.dtos;

public class AdminDTO extends UserDTO {

    private int id;

    public AdminDTO() {
        this.id = -1;
        super.username = "";
        super.password = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return super.username;
    }

    public void setUsername(String username) {
        super.username = username;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
