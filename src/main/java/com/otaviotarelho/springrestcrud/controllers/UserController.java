package com.otaviotarelho.springrestcrud.controllers;

import com.otaviotarelho.springrestcrud.domains.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/User")
public class UserController {

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return null;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insertUser(){
        return null;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Integer id){
        return null;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        return null;
    }

}
