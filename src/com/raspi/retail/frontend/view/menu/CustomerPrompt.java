package com.raspi.retail.frontend.view.menu;

public class CustomerPrompt extends MainPrompt{

    public static void printCustomerLoginPrompt(){
        System.out.println("\nCUSTOMER MENU");
        System.out.println("===============");
    }

    public static void printCustomerFunctions(){
        System.out.println("\nLogin Successful: CUSTOMER" +
                "\n\nSelect a function:" +
                "\n[VP]: View Products" +
                "\n[A]: Add to Cart" +
                "\n[VC]: View Cart" +
                "\n[X]: Terminate Program");
    }
}
