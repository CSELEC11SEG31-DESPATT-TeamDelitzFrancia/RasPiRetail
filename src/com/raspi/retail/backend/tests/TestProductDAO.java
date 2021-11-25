package com.raspi.retail.backend.tests;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.backend.util.DBDriverService;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestProductDAO {

    ModelFactory dtoF = ModelFactoryProducer.createFactory("dto");
    ModelFactory daoF = ModelFactoryProducer.createFactory("dao");
    ProductDAO pDAO = (ProductDAO) daoF.createDAOPrototype("product");
    ProductDTO testProduct = (ProductDTO) dtoF.createDTOPrototype("product");
    ProductDTO foundProduct = (ProductDTO) dtoF.createDTOPrototype("product");


    public TestProductDAO() {
        testProduct.setName("Test Product");
        testProduct.setStock(100);
        testProduct.setPrice(25.00);
        testProduct.setProductType("Mainline");
        testProduct.setDescription("This is a test product, intended for testing purposes.");
        testProduct.setUrlListing("https://samplesite.com/products/testproduct");
    }

    public ProductDTO getTestProduct() {
        return testProduct;
    }

    public ProductDTO getFoundProduct() {
        ArrayList<ProductDTO> foundProducts = pDAO.getProductByName(getTestProduct().getName());
        if (foundProducts.size() >= 1)
            return foundProducts.get(0);
        else
            return null;
    }


    // INIT Method
    @Test
    public void initTablesIfNotExists() {
        DBDriverService.buildSqlDatabase();
    }

    /*
         _           _
        | |_ ___ ___| |_ ___
        | __/ _ / __| __/ __|
        | ||  __\__ | |_\__ \
        \__\___|___/\__|___/
     */
    @Test
    public void testGetAllProducts() {
        ArrayList<ProductDTO> products = pDAO.getAllProducts();
        assertTrue("Check if there are products returned.", products.size() >= 6);
    }

    @Test
    public void testGetProductById() {
        int selectedID = 2;
        ProductDTO foundProduct = pDAO.getProductById(selectedID);
        assertEquals("Check if selected ID is found properly.", foundProduct.getId(), selectedID);
    }

    @Test
    public void testGetProductByName() {
        String nameQuery = "Model";
        ArrayList<ProductDTO> foundProducts = pDAO.getProductByName(nameQuery);
        assertTrue("Check if name query searches up the right number of products.", foundProducts.size() >= 2);
    }

    @Test
    public void testGetProductByType() {
        String typeQuery = "Micro";
        ArrayList<ProductDTO> foundProducts = pDAO.getProductByType(typeQuery);
        assertEquals("Check if type query searches up the right number of products.", 3, foundProducts.size());
    }

    @Test
    public void testAddProduct() {
        pDAO.addProduct(getTestProduct());
        assertEquals("Check if product is added properly by comparing test product with found product.", getTestProduct().getName(), getFoundProduct().getName());
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO updatedProduct = (ProductDTO) dtoF.createDTOPrototype("product");
        updatedProduct.setStock(200);
        updatedProduct.setPrice(30.20);
        pDAO.updateProduct(getFoundProduct().getId(), updatedProduct);
        assertTrue("Check if product stock is updated.", getFoundProduct().getStock() == 200 && getFoundProduct().getPrice() == 30.20);
    }

    @Test
    public void testDeleteProduct() {
        int productID = getFoundProduct().getId();
        pDAO.deleteProduct(productID);
        assertNull("Check if product has been properly deleted.", getFoundProduct());
    }

}
