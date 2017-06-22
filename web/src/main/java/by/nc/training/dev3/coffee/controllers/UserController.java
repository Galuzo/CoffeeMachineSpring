package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.UserForRegisterDto;
import by.nc.training.dev3.coffee.exceptions.RegisterException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Win on 04.06.2017.
 */
@RestController
@RequestMapping()
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private String message;

    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value ="/register",method = RequestMethod.POST)
    public void register (@RequestBody UserForRegisterDto userForRegisterDto) {
        try {
            authorizationService.registration(userForRegisterDto);
            message = "register was success";
            LOGGER.info(message);
        } catch (RegisterException e) {
            message = "register is not success";
            LOGGER.error(message+e.toString());          }
    }



}
