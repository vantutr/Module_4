package com.codegym.thuchanh2.controller;

import com.codegym.thuchanh2.jwt.service.JwtResponse;
import com.codegym.thuchanh2.jwt.service.JwtService;
import com.codegym.thuchanh2.model.User;
import com.codegym.thuchanh2.model.dto.UserDTO;
import com.codegym.thuchanh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found User", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.add(user)) {
            return new ResponseEntity<>("User created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userInfo = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(userInfo.getId(), jwt,
                userInfo.getUsername(), userInfo.getUsername(), userDetails.getAuthorities()));
    }
}
