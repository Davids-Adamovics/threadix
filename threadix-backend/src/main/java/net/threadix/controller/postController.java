package net.threadix.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.threadix.model.Post;
import net.threadix.repo.IPostRepo;
import net.threadix.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/posts")
public class postController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private IPostService postService;

    @Autowired
    private IPostRepo postRepository;

    @GetMapping("/all")
    public ArrayList<Post> getPreceCRUDAll() {
        try {
            return postService.retrieveAll();
        } catch (Exception e) {
            return null;
        }
    }

    // Upload image
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path path = Path.of(UPLOAD_DIR + fileName);
        
        try {
            // Create the upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the file
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Return the file path or URL (can be used in the frontend)
            return ResponseEntity.ok("/uploads/" + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if (post.getTimestamp() == null) {
            post.setTimestamp(LocalDateTime.now());
        }

        postRepository.save(post);
        return ResponseEntity.ok(post);
    }
}
