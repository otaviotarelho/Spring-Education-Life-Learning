package edu.otaviotarelho.userservice.controller;

import edu.otaviotarelho.userservice.domain.User;
import edu.otaviotarelho.userservice.domain.dto.UserDTO;
import edu.otaviotarelho.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value="/user")
@Api(value="User Creation System")
public class UserController {

    private UserService service;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService service, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value="List a specific users in the system", response = User.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successfully retrieved the user")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> get(@ApiParam(value = "User Id", required = true) @PathVariable("id") Integer id){
        User user = service.findById(id);
        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);

        return ok().body(userDTO);
    }

    @ApiOperation(value="Insert a user into database", response = UserDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successfully created the user")
    })
    @PostMapping
    public ResponseEntity<UserDTO> insert(@ApiParam(value = "User object from database", required = true) @Valid @RequestBody User user){
        user.setSignUpDate(LocalDate.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user = service.insert(user);

        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();

        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);

        return created(userUri).body(userDTO);
    }

    @ApiOperation(value="Upadate a user into database", response = UserDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successfully updated the user")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@ApiParam(value = "User id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "User object from database to be updated", required = true) @Valid @RequestBody User user) {
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

    @ApiOperation(value="Update a user into database", response = UserDTO.class)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@ApiParam(value = "User id", required = true) @PathVariable("id") Integer id){
        service.delete(id);
        return noContent().build();
    }

    @ApiOperation(value="List all users in the system", response = List.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        Iterable<User> users = service.findAll();

        Type listType = new TypeToken<List<UserDTO>>() {}.getType();

        List<UserDTO> userDTOS = new ModelMapper().map(users, listType);

        return ResponseEntity.ok().body(userDTOS);
    }
}
