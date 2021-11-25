package com.raspi.retail.frontend.view.menu;

public class GuestPrompt extends MainPrompt{

    public static void printGuestLoginPrompt(){
        System.out.println("\nGUEST MENU");
        System.out.println("===============");
    }

    public static void printGuestFunctions(){
        System.out.println("\nSelect a function:" +
                "\n[VP]: View Products" +
                "\n[SU]: Sign Up" +
                "\n[A]: Add to Cart" +
                "\n[VC]: View Cart" +
                "\n[X]: Terminate Program");
    }
}
