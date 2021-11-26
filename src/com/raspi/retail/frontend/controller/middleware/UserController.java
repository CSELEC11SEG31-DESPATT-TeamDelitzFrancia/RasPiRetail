package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.LoginQuery;
import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.UserDAO;
import com.raspi.retail.backend.model.dtos.CreditCard;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.model.factory.DAOFactory;
import com.raspi.retail.backend.model.factory.DTOFactory;
import com.raspi.retail.backend.util.KBInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserController {

    private static ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    private static ModelFactory dtoFactory = ModelFactoryProducer.createFactory("dto");

    private UserDAO uDao = (UserDAO) daoFactory.createDAOPrototype("user");
    private MemberDTO member = (MemberDTO) dtoFactory.createDTOPrototype("customer");

    private static LoginQuery loginQueryMethods = new LoginQuery();

    public void signUp() {
        String username;
        String password;
        String firstName;
        String lastName;

        System.out.println("\nEnter the following details to sign up: ");

        member.setUsername(KBInput.readString("Username: "));
        member.setEmail(KBInput.readString("Email: "));
        member.setPassword(KBInput.readString("Password: "));
        member.setFirstName(KBInput.readString("First Name: "));
        member.setLastName(KBInput.readString("Last Name: "));
        member.setAddressLine1(KBInput.readString("Address Line 1: "));
        member.setAddressLine2(KBInput.readString("Address Line 2 (optional, press ENTER to skip): "));
        member.setCcNo(new CreditCard(KBInput.readString("Enter Credit Card No.: ")));

        uDao.addUser(member);
    }

    public int setCurrentMemberID(String username){

        MemberDTO currentSessionMember = (MemberDTO) uDao.getOneUserByUsername(CustomerType.CUSTOMER, username);

        return currentSessionMember.getId();
    }
}
