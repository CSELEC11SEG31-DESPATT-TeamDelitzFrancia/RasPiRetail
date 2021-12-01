package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.view.display.ProductDisplay;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductController implements Facade {

    ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    ModelFactory dtoFactory = ModelFactoryProducer.createFactory("dto");
    ProductDAO pDao = (ProductDAO) daoFactory.createDAOPrototype("product");
    ProductDTO product = (ProductDTO) dtoFactory.createDTOPrototype("product");


    @Override
    public void viewAll() {
        Iterator<ProductDTO> products = pDao.getAllProducts();
        ProductDisplay.displayProducts(products);
    }

    public void viewOneById(int id) {
        ProductDTO foundProduct = pDao.getProductById(id);
        ProductDisplay.displayProduct(foundProduct);
    }

    public void viewOneByName(String nameQuery) {
        Iterator<ProductDTO> foundProducts = pDao.getProductByName(nameQuery);
        ProductDisplay.displayProducts(foundProducts);
    }

    public void viewOneByType(String typeQuery) {
        Iterator<ProductDTO> foundProducts = pDao.getProductByType(typeQuery);
        ProductDisplay.displayProducts(foundProducts);
    }

    public void createOne() {
        String prodName;
        int stock;
        double price;
        String productType;
        String description;
        String urlListing;

        System.out.println("\nEnter the following details to CREATE a product: ");

        product.setName(KBInput.readString("Product Name: "));
        product.setStock(KBInput.readInt("Stock: "));
        product.setPrice(KBInput.readDouble("Price: "));
        product.setProductType(KBInput.readString("Product Type: "));
        product.setDescription(KBInput.readString("Description: "));
        product.setUrlListing(KBInput.readString("URL Listing: "));

        pDao.addProduct(product);
    }

    public void updateOne(int id) {
        String prodName;
        int stock;
        double price;
        String productType;
        String description;
        String urlListing;

        System.out.println("\nEnter the following details to UPDATE a product: ");

        product.setName(KBInput.readString("Product Name: "));
        product.setStock(KBInput.readInt("Stock: "));
        product.setPrice(KBInput.readDouble("Price: "));
        product.setProductType(KBInput.readString("Product Type: "));
        product.setDescription(KBInput.readString("Description: "));
        product.setUrlListing(KBInput.readString("URL Listing: "));

        pDao.updateProduct(id, product);
    }

    public void deleteOne(int id) {
        pDao.deleteProduct(id);
    }

    @Override
    public void updateOne() {

    }

    @Override
    public void deleteOne() {

    }
}
