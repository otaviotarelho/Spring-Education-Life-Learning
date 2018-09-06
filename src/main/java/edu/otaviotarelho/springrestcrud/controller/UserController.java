package edu.otaviotarelho.springrestcrud.controller;

import edu.otaviotarelho.springrestcrud.domain.User;
import edu.otaviotarelho.springrestcrud.domain.dto.UserDTO;
import edu.otaviotarelho.springrestcrud.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Integer id){
        User user = service.findById(id);
        return ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody User user){
        user.setSignUpDate(LocalDate.now());
        user = service.insert(user);

        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();

        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);

        return created(userUri).body(userDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody User user) {
        user.setId(id);
        user = service.update(user);

        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();
        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
        return created(userUri).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        Iterable<User> users = service.findAll();

        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOS = new ModelMapper().map(users, listType);

        return ResponseEntity.ok().body(userDTOS);
    }
}
