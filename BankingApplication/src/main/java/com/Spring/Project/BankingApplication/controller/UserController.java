package com.Spring.Project.BankingApplication.controller;

import com.Spring.Project.BankingApplication.dto.BankResponse;
import com.Spring.Project.BankingApplication.dto.UserRequest;
import com.Spring.Project.BankingApplication.entity.User;
import com.Spring.Project.BankingApplication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
  @Autowired
    UserServiceImpl userServiceimpl;

   @PostMapping("/add")
  BankResponse createAccount(@RequestBody UserRequest userRequest){
     return  userServiceimpl.createAccount(userRequest);
  }

  @GetMapping("/get")

    public List<User> getAllUser(){
       return userServiceimpl.getAllUser();
  }
}
