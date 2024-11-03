package net.threadix;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DemoApplicationTests  {

        public static void main(String[] args) {
                SpringApplication.run(DemoApplicationTests.class, args);
        }

        @Bean
        public CommandLineRunner testDatabase() {
                return new CommandLineRunner() {

                        @Override
                        public void run(String... args) throws Exception {

                        }

                };
        }
}
