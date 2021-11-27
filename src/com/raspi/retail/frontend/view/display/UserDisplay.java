package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.model.dtos.UserDTO;

import java.util.ArrayList;

public class UserDisplay {

    public static void displayUsers(ArrayList<UserDTO> users) {

        if(users.size() > 0) {

            if (users.get(0) instanceof MemberDTO) {
                    users.forEach(user -> {
                        MemberDTO currMember = (MemberDTO) user;
                        System.out.println("\n============================================================================");
                        System.out.println("[" + currMember.getId() + "]: " + currMember.getUsername());
                        System.out.println("Name: " + currMember.getLastName() + ", " + currMember.getFirstName());
                        System.out.println("Email: " + currMember.getEmail());
                        System.out.println("Address: \n\t" + currMember.getAddressLine1() + "\n\t" + currMember.getAddressLine2());
                        System.out.println("CCNo: " + currMember.getCcNo().getCcNo());
                        System.out.println("============================================================================\n");
                    });
                }
            else if (users.get(0) instanceof GuestDTO) {
                users.forEach(user -> {
                    GuestDTO currGuest = (GuestDTO) user;
                    System.out.println("\n============================================================================");
                    System.out.println("[" + currGuest.getId() + "]: " + currGuest.getEmail());
                    System.out.println("Name: " + currGuest.getLastName() + ", " + currGuest.getFirstName());
                    System.out.println("Address: \n\t" + currGuest.getAddressLine1() + "\n\t" + currGuest.getAddressLine2());
                    System.out.println("CCNo: " + currGuest.getCcNo().getCcNo());
                    System.out.println("============================================================================\n");

                });
            }
            else if (users.get(0) instanceof AdminDTO) {
                users.forEach(user -> {
                    AdminDTO currAdmin = (AdminDTO) user;
                    System.out.println("\n============================================================================");
                    System.out.println("[" + currAdmin.getId() + "]: " + currAdmin.getUsername());
                    System.out.println("Username: " + currAdmin.getUsername());
                    System.out.println("Password: " + currAdmin.getPassword());
                    System.out.println("============================================================================\n");
                });
            }
            else {
                System.out.println("Invalid User Type.");
            }

        } else {
            System.out.println("There are no users.");
        }

    }

    public static void displayUser(UserDTO user) {

        if(user != null) {

            if(user instanceof MemberDTO) {
                MemberDTO foundMember = (MemberDTO) user;
                System.out.println("FOUND CUSTOMER: ");
                System.out.println();
                System.out.println("\n============================================================================");
                System.out.println("[" + foundMember.getId() + "]: " + foundMember.getUsername());
                System.out.println("Name: " + foundMember.getLastName() + ", " + foundMember.getFirstName());
                System.out.println("Email: " + foundMember.getEmail());
                System.out.println("Address: \n\t" + foundMember.getAddressLine1() + "\n\t" + foundMember.getAddressLine2());
                System.out.println("CCNo: " + foundMember.getCcNo().getCcNo());
                System.out.println("============================================================================\n");
            }
            else if(user instanceof GuestDTO) {
                GuestDTO foundGuest = (GuestDTO) user;
                System.out.println("FOUND GUEST: ");
                System.out.println("\n============================================================================");
                System.out.println("[" + foundGuest.getId() + "]: " + foundGuest.getEmail());
                System.out.println("Name: " + foundGuest.getLastName() + ", " + foundGuest.getFirstName());
                System.out.println("Address: \n\t" + foundGuest.getAddressLine1() + "\n\t" + foundGuest.getAddressLine2());
                System.out.println("CCNo: " + foundGuest.getCcNo().getCcNo());
                System.out.println("============================================================================\n");
            }
            else if(user instanceof AdminDTO) {
                AdminDTO foundAdmin = (AdminDTO) user;
                System.out.println("FOUND ADMIN: ");
                System.out.println("\n============================================================================");
                System.out.println("[" + foundAdmin.getId() + "]: " + foundAdmin.getUsername());
                System.out.println("Username: " + foundAdmin.getUsername());
                System.out.println("Password: " + foundAdmin.getPassword());
                System.out.println("============================================================================\n");
            }
            else {
                System.out.println("Invalid user type.");
            }

        } else {
            System.out.println("User does not exist.");
        }
    }


}
