package com.Spring.Project.BankingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BankResponse {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

}
