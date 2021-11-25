package com.raspi.retail.frontend.view.menu;

import com.raspi.retail.frontend.view.component.MenuComponents;

public class MainPrompt {

        public static void welcome() {
        MenuComponents.generateTopSingleLineBar(20);
        System.out.print("Welcome to RasPi Retail!");
        MenuComponents.generateTopSingleLineBar(20);
    }

    public static void loginOptions() {
        System.out.println();
        System.out.println("Please select a login option: ");
        System.out.println("    [A]: Admin Login");
        System.out.println("    [C]: Customer Login");
        System.out.println("    [G]: Guest Login");
        System.out.println();
        System.out.println("Type [E] to exit.");
    }

    public static void productSearchOptions() {
        MenuComponents.generateTopDoubleLineBar(25);
        System.out.println("SEARCH FOR PRODUCTS");
        MenuComponents.generateBottomDoubleLineBar(25);
        System.out.println();
        System.out.println("How would you like to search for our products?");
        System.out.println();
        MenuComponents.generateTopDoubleLineBar(25);
        System.out.println("    [VA]: View all products");
        System.out.println("    [ID]: Search product by PRODUCT ID");
        System.out.println("    [PN]: Search product by NAME");
        System.out.println("    [PT]: Search product by PRODUCT TYPE");
        MenuComponents.generateBottomSingleLineBar(25);
        System.out.println();
        System.out.println("Type [C] to cancel.");

    }

}
