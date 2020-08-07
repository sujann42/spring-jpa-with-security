package com.rest.controller;

import com.rest.exception.UserNotFoundException;
import com.rest.model.User;
import com.rest.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserDAOService daoService;

    //Retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return daoService.findAll();
    }

    //Get one user
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = daoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("ID- " + id);
        }

        //"all-users", SERVER_PATH + "/users"
        //retrieve all Users
        //HATEOAS
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("All Users"));

        return resource;
    }

    //@RequestBody maps the detail to the variables on the model class [User]
    //Save one user
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = daoService.save(user);
        //Status of Created User
        //Get the uri of the current user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
        //This will provide a status of 201[created] 200 = success
    }

    //Delete an User
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = daoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("ID- " + id);
        }
    }

}
