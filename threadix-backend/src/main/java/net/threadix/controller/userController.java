package net.threadix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.threadix.DTO.LoginDTO;
import net.threadix.DTO.UserDTO;
import net.threadix.model.LoginMessage;
import net.threadix.service.IUserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/users")
public class userController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {

        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    // // Get logged-in user's profile information
    // @GetMapping("/profile")
    // public ResponseEntity<UserDTO> getLoggedInUser(@RequestHeader("Authorization") String authToken) {
    //     // Assume the auth token contains the user's ID or username
    //     UserDTO userProfile = userService.getUserProfile(authToken);
    //     return ResponseEntity.ok(userProfile);
    // }
    
}
