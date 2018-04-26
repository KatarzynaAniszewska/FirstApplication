package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sun.text.resources.no.CollationData_no;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    TemplateEngine templateEngine;
    @Autowired
    TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {

        List<String>functionality = new ArrayList<>();
        functionality.add("You can manage Your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending task to Trello");

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_urL","http://localhost:8080/v1/task");
        context.setVariable("button","Visit website");
        context.setVariable("admin_config",adminConfig);
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("company_name",adminConfig.getCompanyName());
        context.setVariable("company_email",adminConfig.getCompanyEmail());
        context.setVariable("company_goal",adminConfig.getCompanyGoal());
        context.setVariable("company_phone",adminConfig.getCompanyPhone());
        context.setVariable("preview_message","This is a message from TaskApplication");
        context.setVariable("goodbye_message","The end");
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("application_functionality",functionality);
        return templateEngine.process("mail/created-trello-card-mail",context);
    }
    public String buildTaskEmail(String message) throws InterruptedException {
        Context context = new Context();
        context.setVariable("task_urL","http://localhost:8080/v1/task");
        context.setVariable("message","Current number of tasks is "+taskRepository.count());
        return templateEngine.process("mail/created-tasks-mail", context);
    }
}
