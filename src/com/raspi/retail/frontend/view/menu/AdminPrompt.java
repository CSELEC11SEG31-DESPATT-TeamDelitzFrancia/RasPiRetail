package com.raspi.retail.frontend.view.menu;

public class AdminPrompt extends MainPrompt {

    public static void printAdminLoginPrompt(){
        System.out.println("\nADMIN MENU");
        System.out.println("===============");
    }

    public static void printAdminFunctions(){
        System.out.println("\nLogin Successful: ADMIN" +
                "\n\nSelect a function:" +
                "\n[U]: Update Products" +
                "\n[A]: Add Products" +
                "\n[D]: Delete Products" +
                "\n[VP]: View All Products" +
                "\n[PM]: Package Manager" +
                "\n[X]: Terminate Program");
    }

    public static void printAdminPackageFunctions(){
        System.out.println("\nSelect a package manager function: " +
                "\n\n[VAP]: View All Packages" +
                //"\n[UID]: View Package by User ID" +
                "\n[RP]: Remove Package");
    }
}
