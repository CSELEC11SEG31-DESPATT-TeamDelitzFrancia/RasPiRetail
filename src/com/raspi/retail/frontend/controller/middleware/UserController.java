package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.UserDAO;
import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.CreditCard;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.KBInput;

public class UserController {

    private static ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    private static ModelFactory dtoFactory = ModelFactoryProducer.createFactory("dto");

    private UserDAO uDao = (UserDAO) daoFactory.createDAOPrototype("user");
    private MemberDTO member = (MemberDTO) dtoFactory.createDTOPrototype("customer");
    private GuestDTO guest = (GuestDTO) dtoFactory.createDTOPrototype("guest");

    public boolean isLoginValid(CustomerType userType, String username, String password) {
        if(userType == CustomerType.CUSTOMER) {
            MemberDTO foundMember = (MemberDTO) uDao.getOneUserByUsername(userType, username);
            // returns true if username and password are true
            return foundMember.getUsername().equals(username) && foundMember.getPassword().equals(password);
        }
        if(userType == CustomerType.ADMIN) {
            AdminDTO foundAdmin = (AdminDTO) uDao.getOneUserByUsername(userType, username);
            // returns true if username and password are true
            return foundAdmin.getUsername().equals(username) && foundAdmin.getPassword().equals(password);
        }
        // if user is null
        return false;
    }

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
        while(true) {
            String ccNo = KBInput.readString("Enter Credit Card No.: ");
            CreditCard creditCard = new CreditCard(ccNo);
            if(creditCard == null) {
                System.out.println("Invalid Credit Card!");
            } else {
                member.setCcNo(creditCard);
                break;
            }
        }

        uDao.addUser(member);
    }

    public int setCurrentMemberID(String username){

        MemberDTO currentSessionMember = (MemberDTO) uDao.getOneUserByUsername(CustomerType.CUSTOMER, username);

        return currentSessionMember.getId();
    }

    public void signUpAsGuest(String GuestEmail) {

        guest.setEmail(GuestEmail);
        guest.setFirstName(KBInput.readString("First Name: "));
        guest.setLastName(KBInput.readString("Last Name: "));
        guest.setAddressLine1(KBInput.readString("Address Line 1: "));
        guest.setAddressLine2(KBInput.readString("Address Line 2 (optional, press ENTER to skip): "));
        while(true) {
            String ccNo = KBInput.readString("Enter Credit Card No.: ");
            CreditCard creditCard = new CreditCard(ccNo);
            if(creditCard == null) {
                System.out.println("Invalid Credit Card No!");
            } else {
                guest.setCcNo(creditCard);
                break;
            }
        }

        uDao.addUser(guest);
    }

    public int setCurrentGuestID(String email){

        GuestDTO currentSessionGuest = (GuestDTO) uDao.getOneUserByEmail(CustomerType.GUEST, email);

        return currentSessionGuest.getId();
    }
}
