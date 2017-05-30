package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.OrderDto;
import by.nc.training.dev3.coffee.dto.IngredientInOrderDto;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by Win on 25.05.2017.
 */
@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "beverages",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBeverageInBill(@RequestBody OrderDto orderDto)
    {
        try {
            clientService.addBeverageInBill(orderDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "ingredients",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIngredientInBill(@RequestBody IngredientInOrderDto ingredientInOrderDto)
    {
        try {
            clientService.addIngredient(ingredientInOrderDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "beverages",method = RequestMethod.DELETE)
    public void removeBeverageFromBill(@PathParam("userId") int userId,@PathParam("orderId") int orderId) {
        try {
            clientService.removeBeverageFromBill(userId,orderId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "ingredients",method = RequestMethod.DELETE)
    public void removeIngredient(@PathParam("userId") int userId, @PathParam("orderId") int orderId,@PathParam("ingredientId") int ingredientID)
    {
        try {
            clientService.removeIngredient(userId,orderId,ingredientID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
