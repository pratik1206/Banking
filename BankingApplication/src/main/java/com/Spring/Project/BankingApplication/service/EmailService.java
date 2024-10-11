package com.Spring.Project.BankingApplication.service;

import com.Spring.Project.BankingApplication.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);

}
