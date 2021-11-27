package com.raspi.retail.frontend.view.component;

public class MenuComponents {


    public static String generateSingleLineBar(int length) {
        return String.format("%0"+length+"d",0).replace("0","-");
    }

    public static String generateDoubleLineBar(int length) {
        return String.format("%0" + length + "d", 0).replace("0", "=");
    }

    public static void generateTopSingleLineBar(int length) {
        System.out.println("/" + generateSingleLineBar(length) + "\\");
    }

    public static void generateTopDoubleLineBar(int length) {
        System.out.println("/" + generateDoubleLineBar(length) + "\\");
    }

    public static void generateBottomSingleLineBar(int length) {
        System.out.println("\\" + generateDoubleLineBar(length) + "/");
    }

    public static void generateBottomDoubleLineBar(int length) {
        System.out.print("\\" + generateDoubleLineBar(length) + "/");
    }


}
