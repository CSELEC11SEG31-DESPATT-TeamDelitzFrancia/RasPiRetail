package com.raspi.retail.backend.tests;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.UserDAO;
import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.CreditCard;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.util.DBDriverService;
import com.raspi.retail.backend.util.Security;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestUserDAO {

    ModelFactory dtoF = ModelFactoryProducer.createFactory("dto");
    ModelFactory daoF = ModelFactoryProducer.createFactory("dao");
    UserDAO uDAO = (UserDAO) daoF.createDAOPrototype("user");
    MemberDTO testMember = (MemberDTO) dtoF.createDTOPrototype("customer");
    GuestDTO testGuest = (GuestDTO) dtoF.createDTOPrototype("guest");
    AdminDTO testAdmin = (AdminDTO) dtoF.createDTOPrototype("admin");

    public TestUserDAO() {
        // test customer
        testMember.setUsername("testMember11111");
        testMember.setPassword(Security.encrypt("thisIsMyPassword1"));
        testMember.setEmail("testmember11111@email.com");
        testMember.setFirstName("Test");
        testMember.setLastName("Tester");
        testMember.setAddressLine1("Test St.");
        testMember.setAddressLine2("Test City");
        testMember.setCcNo(new CreditCard("4716433767925753"));
        // test guest
        testGuest.setEmail("testguest11111@gmail.com");
        testGuest.setFirstName("Tester");
        testGuest.setLastName("Test");
        testGuest.setAddressLine1("Somewhere St.");
        testGuest.setCcNo(new CreditCard("4716730831919757"));
        // test admin
        testAdmin.setUsername("admin_test");
        testAdmin.setPassword(Security.encrypt("thisisanadminpassword"));
    }

    // INIT Method
    @Test
    public void initTablesIfNotExists() {
        DBDriverService.buildSqlDatabase();
    }

    /*
         _           _
        | |_ ___ ___| |_ ___
        | __/ _ / __| __/ __|
        | ||  __\__ | |_\__ \
        \__\___|___/\__|___/
    */

    @Test
    public void testGetAllRegisteredCustomers() {
        ArrayList<MemberDTO> members = uDAO.getAllUsers(CustomerType.CUSTOMER);
        ArrayList<AdminDTO> admins = uDAO.getAllUsers(CustomerType.ADMIN);

        System.out.print("\n" +
            "  ____          _                                           \n" +
            "/ ____    _ ___| |_ ___  _ __ ___   ___ _ __ ___            \n" +
            "| |  | | | / __| __/ _ \\| '_ ` _ \\ / _ | '__/ __|         \n" +
            "| |__| |_| \\__ | || (_) | | | | | |  __| |  \\__ \\        \n" +
            " \\____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/      \n" +
            "\n");
        members.forEach((member -> {
            System.out.println("\n============================================================================");
            System.out.println("[" + member.getId() + "]: " + member.getUsername());
            System.out.println("Name: " + member.getLastName() + ", " + member.getFirstName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Address: \n\t" + member.getAddressLine1() + "\n\t" + member.getAddressLine2());
            System.out.println("CCNo: " + member.getCcNo().getCcNo());
            System.out.println("============================================================================\n");
        }));



        assertTrue("Check if registered products returned.", members.size() >= 5);


    }

}
