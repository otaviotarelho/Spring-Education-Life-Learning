package edu.otaviotarelho.springrestcrud.controller;

import edu.otaviotarelho.springrestcrud.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Integer id){
        return null;
    }

    @PostMapping
    public ResponseEntity<User> insert(){
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Integer id){
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        return null;
    }

}
