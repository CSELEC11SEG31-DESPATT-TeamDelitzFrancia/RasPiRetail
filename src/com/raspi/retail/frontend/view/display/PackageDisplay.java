package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.PackageDTO;

import java.util.Iterator;

public class PackageDisplay {

    public static void displayPackages(Iterator<PackageDTO> pkgs) {

        if(pkgs.hasNext()) {
            System.out.println("Packages: ");
            System.out.println();
            System.out.println("PackageID\tuserID\tPackageType\ttotalQty\tgrandTotal");
            pkgs.forEachRemaining(pkg -> {
                System.out.print("["+pkg.getPackageID()+"]");
                System.out.print("\t"+pkg.getCustomerID());
                System.out.print("\t"+pkg.getPackageType());
                System.out.print("\t"+pkg.getTotalQty());
                System.out.print("\tUSD "+pkg.getGrandTotal());
                System.out.println();
            });
        } else {
            System.out.println("There are no user packages ready to ship.");
        }

    }

}
