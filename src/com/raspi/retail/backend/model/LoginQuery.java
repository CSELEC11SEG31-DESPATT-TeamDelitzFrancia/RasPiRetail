package com.raspi.retail.backend.model;

import com.raspi.retail.backend.util.DBDriverService;

import java.sql.*;

public class LoginQuery {

    private final Connection dbct = DBDriverService.setupConnection();

    public boolean isLoginValid(String username, String password) {
        try {
            String query = "SELECT username, password FROM RasPiRetail_Users WHERE username= ? AND password= ?";  //get username
            PreparedStatement preparedStatement = this.dbct.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery() ;

            if(rs.next()) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        }
    }


    public boolean isUsernameRegistered(String username) {
        String query = "SELECT username FROM RasPiRetail_Users WHERE username=" +"\""+username+"\""+";";  //get username
        try {
            Statement stmt = dbct.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query) ;
            if(rs!= null) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true ;
        }
    }

    public boolean isPasswordRegistered(String password) {
        String query = "SELECT password FROM RasPiRetail_Users WHERE password= ?";  //get password
        try {
            Statement stmt = dbct.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query) ;
            if(rs!= null) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true ;
        }
    }
}
