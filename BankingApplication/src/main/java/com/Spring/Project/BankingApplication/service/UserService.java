package com.Spring.Project.BankingApplication.service;

import com.Spring.Project.BankingApplication.dto.BankResponse;
import com.Spring.Project.BankingApplication.dto.UserRequest;
import com.Spring.Project.BankingApplication.entity.User;

import java.util.List;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    public List<User> getAllUser();

}
