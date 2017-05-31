package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.OrderDto;
import by.nc.training.dev3.coffee.dto.IngredientInOrderDto;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.ClientService;
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
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/beverages",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBeverageInBill(@RequestBody OrderDto orderDto)
    {
        try {
            clientService.addBeverageInBill(orderDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/ingredients",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIngredientInBill(@RequestBody IngredientInOrderDto ingredientInOrderDto)
    {
        try {
            clientService.addIngredient(ingredientInOrderDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/beverages/{id}",method = RequestMethod.DELETE)
    public void removeBeverageFromBill(@PathVariable("id") int orderId) {
        try {
            clientService.removeBeverageFromBill(orderId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/ingredients",method = RequestMethod.DELETE)
    public void removeIngredient(@PathParam("orderId") int orderId,@PathParam("ingredientId") int ingredientId)
    {
        try {
            clientService.removeIngredient(orderId,ingredientId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
