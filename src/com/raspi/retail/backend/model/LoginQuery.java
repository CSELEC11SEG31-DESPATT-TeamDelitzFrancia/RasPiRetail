package com.raspi.retail.backend.model;

import com.raspi.retail.backend.util.DBDriverService;

import java.sql.*;
import java.util.Random;

public class LoginQuery {

    private final Connection dbct = DBDriverService.setupConnection();

    public boolean isLoginValid(String username, String password) {
        try {
            String query = "SELECT username, password FROM RasPiRetail_Users WHERE username= ? AND password= ?";
            PreparedStatement preparedStatement = this.dbct.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery() ;

            if(rs.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
