package com.example.security.service;

import com.example.security.model.Role;
import com.example.security.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    // Assuming username is unique
    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();
}
