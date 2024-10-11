package com.Spring.Project.BankingApplication.service;

import com.Spring.Project.BankingApplication.dto.AccountInfo;
import com.Spring.Project.BankingApplication.dto.BankResponse;
import com.Spring.Project.BankingApplication.dto.EmailDetails;
import com.Spring.Project.BankingApplication.dto.UserRequest;
import com.Spring.Project.BankingApplication.entity.User;
import com.Spring.Project.BankingApplication.repository.UserRepository;
import com.Spring.Project.BankingApplication.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        /**
         * Creating an account - Saving a new user into DB
         * check user already has account
         */

         if (userRepository.existsByEmail(userRequest.getEmail())){
             return  BankResponse.builder()
                       .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                       .responseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE)
                       .accountInfo(null)
                       .build();

         }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativeNumber(userRequest.getAlternativeNumber())
                .status("Active")
                .build();

         User savedUser = userRepository.save(newUser);
    // Send Email Alerts
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .Subject("Account Creation")
                .MessageBody("Congratulation! Your Account has been successfully created.\n " +
                        "Your Account Details :.\n  Account Name : "+ savedUser.getFirstName() + "   " +savedUser.getLastName() + "  " +savedUser.getOtherName() +"\n Account Number :  "+ savedUser.getAccountNumber())
                .build();
 emailService.sendEmailAlert(emailDetails);
         return BankResponse.builder()
                 .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                 .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                 .accountInfo(AccountInfo.builder()
                         .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                         .accountNumber(savedUser.getAccountNumber())
                         .accountBalance(savedUser.getAccountBalance())
                         .build())
                 .build();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
