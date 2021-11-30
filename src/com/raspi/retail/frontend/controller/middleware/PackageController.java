package com.raspi.retail.frontend.controller.middleware;

import com.raspi.retail.backend.model.ModelFactory;
import com.raspi.retail.backend.model.ModelFactoryProducer;
import com.raspi.retail.backend.model.daos.CartDAO;
import com.raspi.retail.backend.model.daos.PackageDAO;
import com.raspi.retail.backend.model.daos.ProductDAO;
import com.raspi.retail.backend.model.dtos.AdminDTO;
import com.raspi.retail.backend.model.dtos.CartItem;
import com.raspi.retail.backend.model.dtos.PackageDTO;
import com.raspi.retail.backend.model.dtos.ProductDTO;
import com.raspi.retail.backend.model.dtos.customer.GuestDTO;
import com.raspi.retail.backend.model.dtos.customer.MemberDTO;
import com.raspi.retail.backend.model.dtos.enums.CustomerType;
import com.raspi.retail.backend.model.dtos.enums.PackageType;
import com.raspi.retail.backend.util.KBInput;
import com.raspi.retail.frontend.view.display.CartDisplay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PackageController implements Facade{

    ModelFactory daoFactory = ModelFactoryProducer.createFactory("dao");
    ModelFactory dtoFactory = ModelFactoryProducer.createFactory("dto");

    CartDAO cDao = (CartDAO) daoFactory.createDAOPrototype("cart");
    PackageDAO pkDao = (PackageDAO) daoFactory.createDAOPrototype("package");
    ProductDAO pDao = (ProductDAO) daoFactory.createDAOPrototype("product");

    private MemberDTO member = (MemberDTO) dtoFactory.createDTOPrototype("customer");
    private GuestDTO guest = (GuestDTO) dtoFactory.createDTOPrototype("guest");

    public void checkoutCustomer(CustomerType userType, PackageType pkgType, int customerID) {
//        if(pkgType.equals("1")){
//            pkgType = PackageType.valueOf("STANDARD_ELECTROSTATIC");
//        }
//        if(pkgType.equals("2")){
//            pkgType = PackageType.valueOf("BUBBLEWRAP_ELECTROSTATIC");
//        } else {
//            System.out.println("Invalid Entry");
//        }
        // get current cart from user
        ArrayList<CartItem> currUserCart = cDao.getUserCart(customerID, userType);
        // scan through the user's cart to subtract the stock
        currUserCart.forEach((cartItem) -> {
            // set current product data and updated product data
            ProductDTO productToUpdate = pDao.getProductById(cartItem.getProductID());
            // get current product ID
            int currProductID = cartItem.getProductID();
            // get current stock
            int currentProductStock = productToUpdate.getStock();
            // subtract qty from current stock and set as new stock
            productToUpdate.setStock(currentProductStock - cartItem.getQuantity());
            // update product
            pDao.updateProduct(currProductID, productToUpdate);
        });
        // package the cart contents and save to database
        pkDao.addPackage(userType, pkgType, customerID);
    }


    public void adminViewPackages(CustomerType userType){

        ArrayList<PackageDTO> viewAllPackages = pkDao.getPackages(userType);
    }

    public void adminViewPackageByID(CustomerType userType, int packageID){
        //TODO: add package searchbyID
    }

    public void adminDeletePackageByID(CustomerType userType, int pkgID){

        pkDao.removePackage(userType, pkgID);
    }

    //public void addPackage(CustomerType userType, PackageType pkgType, int customerID) {
    //public void removePackage(CustomerType userType, int pkgID)

    @Override
    public void viewAll() {

    }

    @Override
    public void createOne() {

    }

    @Override
    public void updateOne() {

    }

    @Override
    public void deleteOne() {

    }
}
