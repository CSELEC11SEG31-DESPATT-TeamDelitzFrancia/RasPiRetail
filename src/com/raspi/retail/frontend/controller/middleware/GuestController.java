package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.LoginQuery;
import com.raspi.retail.backend.util.KBInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GuestController {

    private static LoginQuery loginQueryMethods = new LoginQuery();

    Scanner input = new Scanner(System.in);
//    InputStreamReader isr=new InputStreamReader(System.in);
//    BufferedReader br=new BufferedReader(isr);

    public void signUp() {
        String username;
        String password;
        String firstName;
        String lastName;

        System.out.println("\nEnter the following details to sign up:");

        System.out.println("Username: ");
        username = input.nextLine();

        System.out.println("Password: ");
        password = input.nextLine();

        System.out.println("First Name: ");
        firstName = input.nextLine();

        System.out.println("Last Name: ");
        lastName = input.nextLine();

        loginQueryMethods.signUpUser(username, password, firstName, lastName);
    }
}
