package net.threadix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

                        }

                };
        }

}
