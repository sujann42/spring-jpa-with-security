package com.rest.controller;

import com.rest.exception.UserNotFoundException;
import com.rest.model.Post;
import com.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResourceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    //Retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    //Get one user
    @GetMapping("/jpa/users/{id}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("ID- " + id);
        }

        //"all-users", SERVER_PATH + "/users"
        //retrieve all Users
        //HATEOAS
        EntityModel<Optional<User>> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("All Users"));

        return resource;
    }

    //Delete an User
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    //@RequestBody maps the detail to the variables on the model class [User]
    //Save one user
    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        //Status of Created User
        //Get the uri of the current user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
        //This will provide a status of 201[created] 200 = success
    }

    //Retrieve all posts for a user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
                throw new UserNotFoundException("ID- " + id);
        }
        return optionalUser.get().getPosts();
    }

    //CREATING A POST under a user
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);
        //Status of Created User
        //Get the uri of the current user
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
        //This will provide a status of 201[created] 200 = success
    }


}
