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
        ArrayList<GuestDTO> guests = uDAO.getAllUsers(CustomerType.GUEST);

//        System.out.println("""
//
//                  ____          _                                          \s
//                / ____    _ ___| |_ ___  _ __ ___   ___ _ __ ___           \s
//                | |  | | | / __| __/ _ \\| '_ ` _ \\ / _ | '__/ __|        \s
//                | |__| |_| \\__ | || (_) | | | | | |  __| |  \\__ \\       \s
//                 \\____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/     \s
//
//                """);
        members.forEach((member -> {
            System.out.println("\n============================================================================");
            System.out.println("[" + member.getId() + "]: " + member.getUsername());
            System.out.println("Name: " + member.getLastName() + ", " + member.getFirstName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Address: \n\t" + member.getAddressLine1() + "\n\t" + member.getAddressLine2());
            System.out.println("CCNo: " + member.getCcNo().getCcNo());
            System.out.println("============================================================================\n");
        }));

        System.out.println("\n\n");

//        System.out.print("""
//                       _           _                \s
//              __ _  __| |_ __ ___ (_)_ __  ___      \s
//             / _` |/ _` | '_ ` _ \\| | '_ \\/ __|   \s
//            | (_| | (_| | | | | | | | | | \\__ \\   \s
//            \\__,_|\\__,_|_| |_| |_|_|_| |_|___/    \s
//                """);
        admins.forEach((admin -> {
            System.out.println("\n============================================================================");
            System.out.println("[" + admin.getId() + "]: " + admin.getUsername());
            System.out.println("Username: " + admin.getUsername());
            System.out.println("Password: " + admin.getPassword());
            System.out.println("============================================================================\n");
        }));

                System.out.println("\n\n");

//        System.out.print("""
//                                      _             \s
//                  __ _ _   _  ___ ___| |_ ___       \s
//                 / _` | | | |/ _ / __| __/ __|      \s
//                | (_| | |_| |  __\\__ | |_\\__ \\   \s
//                \\__, |\\__,_|\\___|___/\\__|___/   \s
//                |___/
//                """);
        guests.forEach((guest -> {
            System.out.println("\n============================================================================");
            System.out.println("[" + guest.getId() + "]: " + guest.getEmail());
            System.out.println("Name: " + guest.getLastName() + ", " + guest.getFirstName());
            System.out.println("Address: \n\t" + guest.getAddressLine1() + "\n\t" + guest.getAddressLine2());
            System.out.println("CCNo: " + guest.getCcNo().getCcNo());
            System.out.println("============================================================================\n");
        }));




        assertTrue("Check if registered products returned.", members.size() >= 5);
        assertTrue("Check if admins returned.", admins.size() >= 1);
        assertTrue("Check if guests returned", guests.size() >= 2);


    }

    @Test
    public void testGetOneCustomer() {
        MemberDTO foundMember = (MemberDTO) uDAO.getOneUserById(CustomerType.CUSTOMER, 1);
        AdminDTO foundAdmin = (AdminDTO) uDAO.getOneUserById(CustomerType.ADMIN, 1);
        GuestDTO foundGuest = (GuestDTO) uDAO.getOneUserById(CustomerType.GUEST, 1);

        System.out.println("\n\n");

        System.out.println("FOUND CUSTOMER: ");
        System.out.println();
        System.out.println("\n============================================================================");
        System.out.println("[" + foundMember.getId() + "]: " + foundMember.getUsername());
        System.out.println("Name: " + foundMember.getLastName() + ", " + foundMember.getFirstName());
        System.out.println("Email: " + foundMember.getEmail());
        System.out.println("Address: \n\t" + foundMember.getAddressLine1() + "\n\t" + foundMember.getAddressLine2());
        System.out.println("CCNo: " + foundMember.getCcNo().getCcNo());
        System.out.println("============================================================================\n");


        System.out.println("\n\n");

        System.out.println("FOUND ADMIN: ");
            System.out.println("\n============================================================================");
            System.out.println("[" + foundAdmin.getId() + "]: " + foundAdmin.getUsername());
            System.out.println("Username: " + foundAdmin.getUsername());
            System.out.println("Password: " + foundAdmin.getPassword());
            System.out.println("============================================================================\n");

        System.out.println("\n\n");

        System.out.println("FOUND GUEST: ");
            System.out.println("\n============================================================================");
            System.out.println("[" + foundGuest.getId() + "]: " + foundGuest.getEmail());
            System.out.println("Name: " + foundGuest.getLastName() + ", " + foundGuest.getFirstName());
            System.out.println("Address: \n\t" + foundGuest.getAddressLine1() + "\n\t" + foundGuest.getAddressLine2());
            System.out.println("CCNo: " + foundGuest.getCcNo().getCcNo());
            System.out.println("============================================================================\n");


        assertEquals("Check if customer is found", 1, foundMember.getId());
        assertEquals("Check if admin is found", 1, foundAdmin.getId());
        assertEquals("Check if guest is found", 1, foundGuest.getId());


    }

}
