package com.crud.tasks.sheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static sun.security.x509.X509CertInfo.SUBJECT;

@Component


public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    //private static final String SUBJECT="Tasks: Once a day email";
    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message = "";
        message = "Currently in database you got: " + size + (size == 1 ? "task" : "tasks");
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message)
        );
    }
    @Scheduled(cron = "0 0 10 * * *")
    public void sendMail() {

        simpleEmailService.sendTaskMail(new Mail());
    }
}

