package com.codegym.baitap1.service;

import com.codegym.baitap1.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public void save(User user) {
        System.out.println("Đã lưu người dùng: " + user.getFirstName() + " " + user.getLastName());
    }
}
