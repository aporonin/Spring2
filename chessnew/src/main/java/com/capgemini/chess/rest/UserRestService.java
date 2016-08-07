package com.capgemini.chess.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.to.PlayerTO;


@Controller
@ResponseBody
public class UserRestService {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestParam("userId") int userId){
        userService.createUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerTO>> getUsers(){
        final List<PlayerTO> users = userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
