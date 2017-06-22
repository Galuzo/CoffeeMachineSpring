package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.IngredientForRemoveFromBeverageDto;
import by.nc.training.dev3.coffee.dto.OrderDto;
import by.nc.training.dev3.coffee.dto.IngredientInOrderDto;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Win on 25.05.2017.
 */
@RestController
@RequestMapping("client")
public class ClientController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    private String message;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/beverages",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBeverageInBill(@RequestBody OrderDto orderDto)
    {
        try {
            clientService.addBeverageInBill(orderDto);
            message = "add beverage in bill was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "add beverage in bill is not success";
            LOGGER.error(message+e.toString());        }
    }
    @RequestMapping(value = "/ingredients",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIngredientInBill(@RequestBody IngredientInOrderDto ingredientInOrderDto)
    {
        try {
            clientService.addIngredient(ingredientInOrderDto);
            message = "add ingredient in bill was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "add ingredient in bill is not success";
            LOGGER.error(message+e.toString());        }
    }

    @RequestMapping(value = "/beverages/{id}",method = RequestMethod.DELETE)
    public void removeBeverageFromBill(@PathVariable("id") int orderId) {
        try {
            clientService.removeBeverageFromBill(orderId);
            message = "remove beverage from bill was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "remove beverage from bill is not success";
            LOGGER.error(message+e.toString());        }
    }

    @RequestMapping(value = "/ingredients",method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeIngredient(@RequestBody IngredientForRemoveFromBeverageDto ingredientForRemoveFromBeverageDto)
    {
        try {
            clientService.removeIngredient(ingredientForRemoveFromBeverageDto);
            message = "remove ingredient from bill was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "remove ingredient from  bill is not success";
            LOGGER.error(message+e.toString());        }
    }

}
