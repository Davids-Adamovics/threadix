package net.threadix.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.threadix.model.Post;
import net.threadix.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("post")
public class postController {

    @Autowired
    private IPostService postService;

    @GetMapping("/all")
    public ArrayList<Post> getPreceCRUDAll() {
        try {
            return postService.retrieveAll();
        } catch (Exception e) {
            return null;
        }
    }

}
