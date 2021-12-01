package com.raspi.retail.frontend.view.display;

import com.raspi.retail.backend.model.dtos.ProductDTO;

import java.util.Iterator;

public class ProductDisplay {


    public static void displayProducts(Iterator<ProductDTO> products) {
        if(products.hasNext()) {
            products.forEachRemaining(product ->  {
                    System.out.println("\n=============================================");
                    System.out.println("[" + product.getId() + "]: " + product.getName());
                    System.out.println("\"" + product.getDescription() + "\"");
                    System.out.println();
                    System.out.println("Type: " + product.getProductType());
                    System.out.println("Price: USD " + String.format("%.2f", product.getPrice()));
                    if ((product.getStock() > 0)) {
                        System.out.println("Stock Remaining: " + product.getStock());
                    } else {
                        System.out.println("Product out of stock.");
                    }
                    System.out.println("URL: " + product.getUrlListing());
                    System.out.println("=============================================\n");
            });
        } else {
            System.out.println("No products exist.");
        }
    }
    public static void displayProduct(ProductDTO product) {
            System.out.println("\n=============================================");
            System.out.println("[" + product.getId() + "]: " + product.getName());
            System.out.println("\""+ product.getDescription() +"\"");
            System.out.println();
            System.out.println("Type: " + product.getProductType());
            System.out.println("Price: USD " + String.format("%.2f", product.getPrice()));
            System.out.println("Stock Remaining: " + product.getStock());
            System.out.println("URL: " + product.getUrlListing());
            System.out.println("=============================================\n");
    }

}
