package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.BillDto;
import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.BillService;
import by.nc.training.dev3.coffee.services.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 25.05.2017.
 */
@RequestMapping("bill")
@RestController
public class BillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);
    private String message;

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/beverages",method = RequestMethod.GET)
    public List<ContentDto>  showBeveragesFromBill() {
        List<ContentDto> list=null;
        try {
            list=billService.showBeveragesInBill();
            message = "show beverage was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "show beverage is not success";
            LOGGER.error(message+e.toString());
        }
        return list;
    }

    @RequestMapping(value = "/ingredients/{id}",method = RequestMethod.GET)
    public Set<ContentDto> showIngredientsInBeverage(@PathVariable("id") int orderId) {
        Set<ContentDto> ingredients=null;
        try {
           ingredients= billService.showIngredientsInBeverage(orderId);
            message = "show ingredient was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "show ingredient is not success";
            LOGGER.error(message+e.toString());        }
        return ingredients;
    }

    @RequestMapping(value ="/orders",method = RequestMethod.GET)
    public List<DetailOrderDto> showOrders() {
        List<DetailOrderDto> list=null;
        try {
            list= billService.showOrders();
            message = "show orders was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "show orders is not success";
            LOGGER.error(message+e.toString());             }
        return list;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public BillDto showGeneralCost() {
        BillDto billDto=null;
        try {
            billDto=billService.showGeneralCost();
            message = "show general cost was success";
            LOGGER.info(message);
        } catch (ServiceException e) {
            message = "show general cost is not success";
            LOGGER.error(message+e.toString());        }
        return billDto;

    }


}
