package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sun.text.resources.no.CollationData_no;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_urL","http://localhost:8080/v1/task");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("company_name",adminConfig.getCompanyName());
        context.setVariable("company_email",adminConfig.getCompanyEmail());
        context.setVariable("company_goal",adminConfig.getCompanyGoal());
        context.setVariable("company_phone",adminConfig.getCompanyPhone());
        context.setVariable("preview_message","This is a message from TaskApplication");
        context.setVariable("goodbye_message","The end");

        return templateEngine.process("mail/created-trello-card-mail",context);
    }




}
