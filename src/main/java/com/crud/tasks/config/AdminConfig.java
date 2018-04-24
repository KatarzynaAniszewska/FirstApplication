package com.crud.tasks.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter

public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;
    @Value("${admin.name}")
    private String adminName;
    @Value("${company.name}")
    private String companyName;
    @Value("${company.goal}")
    private String companyGoal;
    @Value("${company.email}")
    private String companyEmail;
    @Value("${company.phone}")
    private String companyPhone;


}
