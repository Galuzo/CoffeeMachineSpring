package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.ContentForIncDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import by.nc.training.dev3.coffee.services.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Win on 24.05.2017.
 */
@RestController
@RequestMapping("/admin/")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private String message;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "beverages",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExistBeverage(@RequestBody ContentForIncDto contentForIncDto)
    {
        try {
            if(contentForIncDto.getCount()>0) {
                message = "Inc beverage was success";
                adminService.addExistContentInMachine(ContentType.BEVERAGE, contentForIncDto);
                LOGGER.info(message);
            }
        } catch (ServiceException e) {
            message = "Inc beverage is not success";
            LOGGER.error(message+e.toString());
        }
    }

    @RequestMapping(value = "ingredients",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addExistIngredient(@RequestBody ContentForIncDto contentForIncDto )
    {
        try {
            if(contentForIncDto.getCount()>0) {
                message = "Inc ingredient was success";
                LOGGER.info(message);
                adminService.addExistContentInMachine(ContentType.INGREDIENT,contentForIncDto);
            }
        } catch (ServiceException e) {
            message = "Inc ingredient is not success";
            LOGGER.error(message+e.toString());
        }
    }

    @RequestMapping(value = "beverages",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewBeverage(@RequestBody Beverage beverage) {
        try {
            adminService.addNewContentInMachine(ContentType.BEVERAGE, beverage);
            message = "add beverage was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "add beverage is not success";
            LOGGER.error(message+e.toString());        }
    }

    @RequestMapping(value = "ingredients",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewIngredient(@RequestBody Ingredient ingredient) {
        try {
            adminService.addNewContentInMachine(ContentType.BEVERAGE, ingredient);
            message = "add beverage was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "add ingredient is not success";
            LOGGER.error(message+e.toString());          }
    }

    @RequestMapping(value = "beverages/{id}",method = RequestMethod.DELETE)
    public void removeBeverage(@PathVariable("id") int id) {
        try {
            adminService.removeContentFromMachine(ContentType.BEVERAGE,id);
            message = "remove beverage was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "remove beverage is not success";
            LOGGER.error(message+e.toString());        }
    }

    @RequestMapping(value = "ingredients/{id}",method = RequestMethod.DELETE)
    public void removeIngredient(@PathVariable("id") int id) {
        try {
            adminService.removeContentFromMachine(ContentType.INGREDIENT,id);
            message = "remove ingredient was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "remove ingredient is not success";
            LOGGER.error(message+e.toString());        }
    }
}
