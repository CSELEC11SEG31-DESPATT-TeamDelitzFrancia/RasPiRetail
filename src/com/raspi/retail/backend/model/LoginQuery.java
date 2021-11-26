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
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        }
    }

    public String generateCCNum(String genCCNum){
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < 14; i++) {
            int randIndex=rand.nextInt(genCCNum.length());
            res.append(genCCNum.charAt(randIndex));
        }
        return res.toString();
    }

    public void signUpUser(String username,String password,String firstName,String lastName){
        //TODO: for val ccNum create a method that randomly generates a 14 digit number
        //TODO: userType should be customer by default (between password and firstname)

        String userType = "Customer"; //userType should be customer by default
        String genCCNum = "1234567890";
        String ccNo = generateCCNum(genCCNum);

        try {
            String query = "INSERT INTO RasPiRetail_Users (username, password, userType, firstName, lastName, ccNo) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = dbct.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, ccNo);
            preparedStatement.execute();

            dbct.close();

            System.out.println("\nYour account has successfully been created!\n");

        } catch (SQLException e) {
            System.out.println("\nAn error occurred when inserting your data into the database.\n");
            e.printStackTrace();
        }
    }
}
