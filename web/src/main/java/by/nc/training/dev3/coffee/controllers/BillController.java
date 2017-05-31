package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.BillService;
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
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/beverages",method = RequestMethod.GET)
    public List<ContentDto>  showBeveragesFromBill() {
        List<ContentDto> list=null;
        try {
            list=billService.showBeveragesInBill();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping(value = "/ingredients/{id}",method = RequestMethod.GET)
    public Set<ContentDto> showIngredientsInBeverage(@PathVariable("id") int orderId) {
        Set<ContentDto> ingredients=null;
        try {
           ingredients= billService.showIngredientsInBeverage(orderId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @RequestMapping(value ="/orders",method = RequestMethod.GET)
    public List<DetailOrderDto> showOrders() {
        List<DetailOrderDto> list=null;
        try {
            list= billService.showOrders();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list;
    }


}
