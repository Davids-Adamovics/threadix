package net.threadix.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import main.java.net.threadix.DTO.SearchResultDTO;
import net.threadix.DTO.LoginDTO;
import net.threadix.DTO.UserDTO;
import net.threadix.model.LoginMessage;
import org.springframework.web.bind.annotation.RequestParam;
import net.threadix.model.Post;
import net.threadix.repo.IPostRepo;
import net.threadix.repo.IUserRepo;
import net.threadix.service.IUserService;
import net.threadix.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import net.threadix.model.User;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = {
        "http://localhost:5174", // dev
        "http://localhost:3000", // old dev
        "http://77.37.54.78:5174", // prod
        "http://77.37.54.78" // prod
})
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

    @Autowired
    private IPostRepo postRepository;

    @GetMapping("/profile")
    public User getUserProfile(HttpServletRequest request) {
        // Extract the token from the request header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        // Extract the token by removing "Bearer " from the start
        String token = authHeader.substring(7);

        // Extract username from the token
        String username = jwtUtil.extractUsername(token);

        // Fetch the user from the database using the extracted username
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    @GetMapping("/search")
    public SearchResultDTO searchUsersAndPosts(@RequestParam(required = false) String search) {
        String keyword = (search == null) ? "" : search.toLowerCase();

        // Search users
        List<User> users = userRepo.findAll().stream()
                .filter(u -> u.getDisplayName().toLowerCase().contains(keyword)
                        || u.getUsername().toLowerCase().contains(keyword))
                .limit(3)
                .toList();

        // Search posts
        List<Post> posts = StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .filter(p -> p.getTitle().toLowerCase().contains(keyword))
                .limit(5)
                .toList();

        return new SearchResultDTO(users, posts);

    }

}
