package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.UserForRegisterDto;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Win on 04.06.2017.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value ="/register",method = RequestMethod.POST)
    public void register (@RequestBody UserForRegisterDto userForRegisterDto) {
        try {
            authorizationService.registration(userForRegisterDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
