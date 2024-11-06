package net.threadix;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.threadix.model.Comment;
import net.threadix.model.Community;
import net.threadix.model.Group;
import net.threadix.model.Post;
import net.threadix.model.ProfileSettings;
import net.threadix.model.User;
import net.threadix.model.enumNotification_settings;
import net.threadix.model.enumPrivacy_level;
import net.threadix.model.enumVisibility;
import net.threadix.repo.*;

@SpringBootApplication
public class ThreadixApplication {

        public static void main(String[] args) {
                SpringApplication.run(ThreadixApplication.class, args);
        }

        @Bean
        public CommandLineRunner testDatabase(IUserRepo userRepo, IPostRepo postRepo, ICommunityRepo communityRepo,
                        ICommentRepo commentRepo, IProfileSettingsRepo profileSettingsRepo, IGroupRepo groupRepo) {
                return new CommandLineRunner() {

                        @Override
                        public void run(String... args) throws Exception {
                                // Create and save a User
                                User user = new User("john_doe", "John Doe", "john@example.com", "password123", null,
                                                "Just a tech enthusiast.", false);
                                userRepo.save(user);

                                // Create and save a Post
                                Post post = new Post("First Post", "This is the content of the first post.",
                                                LocalDateTime.now(), enumVisibility.PUBLIC, 0, user);
                                postRepo.save(post);

                                // Create and save a Comment, associate it with the Post
                                Comment comment = new Comment("Great post!", LocalDateTime.now(), 10, user, post);
                                commentRepo.save(comment);

                                // Create and save a Community
                                Community community = new Community("Tech Enthusiasts", "A community for tech lovers.",
                                                "PUBLIC", 100);
                                communityRepo.save(community);

                                // Create and save ProfileSettings
                                ProfileSettings profileSettings = new ProfileSettings(enumPrivacy_level.PUBLIC,
                                                enumNotification_settings.ON);
                                profileSettingsRepo.save(profileSettings);

                                // Create and save a Group
                                Group group = new Group("Developers", "A group for software developers.", 50);
                                groupRepo.save(group);
                        }

                };
        }

}
