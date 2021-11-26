package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.frontend.view.display.ProductDisplay;

import java.util.ArrayList;

public class Product implements Facade {

    ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    ModelFactory dtoFactory = ModelFactoryProducer.createFactory("dto");
    ProductDAO pDao = (ProductDAO) daoFactory.createDAOPrototype("product");
    ProductDTO product = (ProductDTO) dtoFactory.createDTOPrototype("product");


    @Override
    public void viewAll() {
        ArrayList<ProductDTO> products = pDao.getAllProducts();
        ProductDisplay.displayProducts(products);
    }

    public void viewOneById(int id) {
        ProductDTO foundProduct = pDao.getProductById(id);
        ProductDisplay.displayProduct(foundProduct);
    }

    public void viewOneByName(String nameQuery) {
        ArrayList<ProductDTO> foundProducts = pDao.getProductByName(nameQuery);
        ProductDisplay.displayProducts(foundProducts);
    }

    public void viewOneByType(String typeQuery) {
        ArrayList<ProductDTO> foundProducts = pDao.getProductByType(typeQuery);
        ProductDisplay.displayProducts(foundProducts);
    }

    @Override
    public void createOne() {
        // TODO: user input of creating a new product
//     e.g.   productname = product.getName();
//        pDao.addProduct();

    }

    @Override
    public void updateOne() {
        // TODO: user input of updating a new product
        // e.g. newproduct = product.getName();
        // pDao.updateProduct();

    }

    @Override
    public void deleteOne() {
        // TODO: user input of deleting a product
        // e.g. selectedid
//        pDao.deleteProduct(selectedid);
    }
}
