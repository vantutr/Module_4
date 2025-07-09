package com.codegym.thuchanh2.service;

import com.codegym.thuchanh2.model.Role;
import com.codegym.thuchanh2.model.User;
import com.codegym.thuchanh2.model.UserPrinciple;
import com.codegym.thuchanh2.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    public static List<User> listUser = new ArrayList<>();
    public static List<Role> listRole = new ArrayList<>();

    public UserService() {
        listRole.add(new Role(1L, "ROLE_ADMIN"));
        listRole.add(new Role(2L, "ROLE_USER"));

        String password = "$2a$10$xMq9EwZvdKUuvgiaM2T1Iuw9A1EGXVZaCIUPEwn1Isa9ffvPqNabe";
        User userAdmin = new User(1L, "admin", password);
        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(listRole.get(0));
        userAdmin.setRoles(roleAdmin);
        User userTu = new User(2L, "tu", password);
        Set<Role> roleTu = new HashSet<>();
        roleTu.add(listRole.get(1));
        userTu.setRoles(roleTu);

        listUser.add(userAdmin);
        listUser.add(userTu);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : listUser) {
            userDTOS.add(toDTO(user));
        }
        return userDTOS;
    }

    public UserDTO findById(Long id) {
        for (User user : listUser) {
            if (Objects.equals(user.getId(), id)) {
                return toDTO(user);
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User user) {
        for (User userExist : listUser) {
            if (Objects.equals(user.getId(), userExist.getId()) || Objects.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }

    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}
