package com.smartpoke.api.controller;

import com.smartpoke.api.model.users.Location;
import com.smartpoke.api.model.users.User;
import com.smartpoke.api.model.users.Userinfo;
import com.smartpoke.api.service.IUserService;
import com.smartpoke.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{userId}/userinfo")
    public ResponseEntity<Userinfo> updateUserInfo(@PathVariable Long userId, @RequestBody Userinfo userInfo) {
        Userinfo updatedUserInfo = userService.updateUserInfo(userId, userInfo);
        if (updatedUserInfo != null) {
            return new ResponseEntity<>(updatedUserInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/location")
    public ResponseEntity<Location> updateLocation(@PathVariable Long userId, @RequestBody Location location) {
        Location updatedLocation = userService.updateLocation(userId, location);
        return ResponseEntity.ok(updatedLocation);
    }
}
