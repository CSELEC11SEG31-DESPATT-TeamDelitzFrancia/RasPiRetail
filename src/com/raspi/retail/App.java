package com.raspi.retail;

import com.raspi.retail.backend.util.DBDriverService;
import com.raspi.retail.frontend.controller.MainMenu;

public class App {

    public static void main(String[] args) {

        // always initialise sql database
        DBDriverService.buildSqlDatabase();
        MainMenu.index();
    }
}
