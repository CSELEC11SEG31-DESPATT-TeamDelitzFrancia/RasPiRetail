package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.PackageDTO;

import java.util.ArrayList;

public class PackageDisplay {

    public static void displayPackages(ArrayList<PackageDTO> pkgs) {

        System.out.println("Packages: ");
        System.out.println();
        System.out.println("PackageID\tuserID\tPackageType\ttotalQty\tgrandTotal");
        pkgs.forEach(pkg -> {
            System.out.print("["+pkg.getPackageID()+"]");
            System.out.print("\t"+pkg.getCustomerID());
            System.out.print("\t"+pkg.getPackageType());
            System.out.print("\t"+pkg.getTotalQty());
            System.out.print("\tUSD "+pkg.getGrandTotal());
            System.out.println();
        });

    }

}
