package com.codegym.thuchanh3.service;

import com.codegym.thuchanh3.model.Role;
import com.codegym.thuchanh3.model.User;
import com.codegym.thuchanh3.model.UserPrinciple;
import com.codegym.thuchanh3.model.dto.UserDTO;
import com.codegym.thuchanh3.repository.IRoleRepository;
import com.codegym.thuchanh3.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    //bean đã được khai báo trước đó, sử dụng để encode/decode mật khẩu
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : iUserRepository.findAll()) {
            userDTOS.add(toDTO(u));
        }
        return userDTOS;
    }

    public UserDTO findById(Long id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.map(this::toDTO).orElse(null);
    }

    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public boolean add(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());   //mã hóa mật khẩu từ String thành mã hóa
        user.setPassword(encodePassword);
        iUserRepository.save(user);
        return true;
    }

    public void delete(Long id) {
        iUserRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) {
        User user = iUserRepository.findByUsername(username);
        if (user != null) {
            return UserPrinciple.build(user);
        }
        return null;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}