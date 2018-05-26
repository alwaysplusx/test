package com.example.hateoas.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hateoas.entity.User;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/u/{username}")
    public UserResource user(@PathVariable("username") String username) {
        User user = new User(username, "foo");
        return new UserResource(user);
    }

    @GetMapping("/all")
    public Resources<UserResource> all() {
        List<User> users = Arrays.asList(new User("foo", "bar"), new User("baz", "qux"));
        Link link = linkTo(UserResource.class).withSelfRel();
        return new Resources<UserResource>(new UserResourceAssembler().toResources(users), link);
    }

    public static class UserResource extends Resource {

        public UserResource(User user) {
            super(user);
            add(linkTo(methodOn(UserController.class).user(user.getUsername())).withSelfRel());
        }

    }

    public static class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

        public UserResourceAssembler() {
            super(User.class, UserResource.class);
        }

        @Override
        public UserResource toResource(User entity) {
            return new UserResource(entity);
        }

    }

}
