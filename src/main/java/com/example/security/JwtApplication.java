package com.example.security;

import com.example.security.model.AppUser;
import com.example.security.model.Role;
import com.example.security.service.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    /*
       This will run after the application started
     */
    @Bean
    CommandLineRunner run(UserServiceImpl userService) {
        return args -> {

            LoggerFactory.getLogger(JwtApplication.class).info("Initializing default data");
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new AppUser(null, "John", "JohnDoe","JohnDoe@company.com","1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "Jane", "JaneDoe","JaneDoe@company.com","1234", new ArrayList<>()));

            userService.addRoleToUser("JohnDoe","ROLE_USER");
            userService.addRoleToUser("JaneDoe","ROLE_USER");
            userService.addRoleToUser("JohnDoe","ROLE_MANAGER");
            userService.addRoleToUser("JohnDoe","ROLE_SUPER_ADMIN");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
