package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.UserDTO;

import java.util.Iterator;

public class UserDisplay {

    public static void displayUsers(Iterator<UserDTO> users) {

        if(users.hasNext()) {
        UserDTO firstUser = users.next();

            if (firstUser instanceof MemberDTO) {
                MemberDTO firstMember = (MemberDTO) firstUser;
                System.out.println("\n============================================================================");
                System.out.println("[" + firstMember.getId() + "]: " + firstMember.getUsername());
                System.out.println("Name: " + firstMember.getLastName() + ", " + firstMember.getFirstName());
                System.out.println("Email: " + firstMember.getEmail());
                System.out.println("Address: \n\t" + firstMember.getAddressLine1() + "\n\t" + firstMember.getAddressLine2());
                System.out.println("CCNo: " + firstMember.getCcNo().getCcNo());
                System.out.println("============================================================================\n");
                users.forEachRemaining(user -> {
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
            else if (firstUser instanceof GuestDTO) {
                GuestDTO firstGuest = (GuestDTO) firstUser;
                System.out.println("\n============================================================================");
                System.out.println("[" + firstGuest.getId() + "]: " + firstGuest.getEmail());
                System.out.println("Name: " + firstGuest.getLastName() + ", " + firstGuest.getFirstName());
                System.out.println("Address: \n\t" + firstGuest.getAddressLine1() + "\n\t" + firstGuest.getAddressLine2());
                System.out.println("CCNo: " + firstGuest.getCcNo().getCcNo());
                System.out.println("============================================================================\n");
                users.forEachRemaining(user -> {
                    GuestDTO currGuest = (GuestDTO) user;
                    System.out.println("\n============================================================================");
                    System.out.println("[" + currGuest.getId() + "]: " + currGuest.getEmail());
                    System.out.println("Name: " + currGuest.getLastName() + ", " + currGuest.getFirstName());
                    System.out.println("Address: \n\t" + currGuest.getAddressLine1() + "\n\t" + currGuest.getAddressLine2());
                    System.out.println("CCNo: " + currGuest.getCcNo().getCcNo());
                    System.out.println("============================================================================\n");

                });
            }
            else if (firstUser instanceof AdminDTO) {
                AdminDTO firstAdmin = (AdminDTO) firstUser;
                System.out.println("\n============================================================================");
                System.out.println("[" + firstAdmin.getId() + "]: " + firstAdmin.getUsername());
                System.out.println("Username: " + firstAdmin.getUsername());
                System.out.println("Password: " + firstAdmin.getPassword());
                System.out.println("============================================================================\n");
                users.forEachRemaining(user -> {
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
