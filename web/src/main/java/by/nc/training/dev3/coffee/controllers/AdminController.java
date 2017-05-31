package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.awt.*;

/**
 * Created by Win on 24.05.2017.
 */
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "beverages",method = RequestMethod.PUT)
    public void addExistBeverage(@PathParam("id") int id, @PathParam("count") int count )
    {
        try {
            if(count>0) {
                adminService.addExistContentInMachine(ContentType.BEVERAGE, id, count);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "ingredients",method = RequestMethod.PUT)
    public void addExistIngredient(@PathParam("id") int id, @PathParam("count") int count )
    {
        try {
            if(count>0) {
                adminService.addExistContentInMachine(ContentType.INGREDIENT, id, count);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "beverages",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewBeverage(@RequestBody Beverage beverage) {
        try {
            adminService.addNewContentInMachine(ContentType.BEVERAGE, beverage);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "ingredients",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewIngredient(@RequestBody Ingredient ingredient) {
        try {
            adminService.addNewContentInMachine(ContentType.BEVERAGE, ingredient);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "beverages/{id}",method = RequestMethod.DELETE)
    public void removeBeverage(@PathVariable("id") int id) {
        try {
            adminService.removeContentFromMachine(ContentType.BEVERAGE,id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "ingredients/{id}",method = RequestMethod.DELETE)
    public void removeIngredient(@PathVariable("id") int id) {
        try {
            adminService.removeContentFromMachine(ContentType.INGREDIENT,id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
