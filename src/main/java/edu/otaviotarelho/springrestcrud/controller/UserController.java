package edu.otaviotarelho.springrestcrud.controller;

import edu.otaviotarelho.springrestcrud.domain.User;
import edu.otaviotarelho.springrestcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Integer id){
        User user = service.findById(id);
        return ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> insert(@Valid @RequestBody User user){
        user = service.insert(user);
        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();

        return created(userUri).body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Integer id, @Valid @RequestBody User user) {
        user.setId(id);
        user = service.update(user);

        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();

        return created(userUri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return noContent().build();
    }

}
