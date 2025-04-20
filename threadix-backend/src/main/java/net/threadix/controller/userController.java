package net.threadix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import net.threadix.DTO.LoginDTO;
import net.threadix.DTO.UserDTO;
import net.threadix.model.LoginMessage;
import net.threadix.model.User;
import net.threadix.repo.IUserRepo;
import net.threadix.service.IUserService;
import net.threadix.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5174")
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

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserRepo userRepo;

    @GetMapping("/profile")
    public User getUserProfile(HttpServletRequest request) {
        // Extract the token from the request
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        // Fetch user from the database
        return userRepo.findByUsername(username);
    }
}

