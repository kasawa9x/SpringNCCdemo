package com.example.springnccdemo.service;

import com.example.springnccdemo.configuration.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CronJobService {

    public static final String MY_EMAIL = "son.nguyenhong.ncc@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "Anhyeuem_99";

    // And receiver!
//    public static final String FRIEND_EMAIL = "sonbuitung@gmail.com";
    public static final String FRIEND_EMAIL = "hongsonaaa@gmail.com";
    @Autowired
    private JavaMailSender emailSender;
    @Scheduled(fixedDelay = 1 * 1000 * 60)
//    @Scheduled(cron = "*/1 * * * * ?")
//    @Async
    public void CronJobService() throws MessagingException {
        System.out.println("sendHtmlEmail");

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<img src='https://www.brainybeaver.com/wp-content/uploads/2017/06/apple-store-logo.jpg>'";

        message.setContent(htmlMsg, "text/html");

        helper.setFrom(MyConstants.MY_EMAIL);

        helper.setTo(MyConstants.FRIEND_EMAIL);

        helper.setSubject("Test send HTML email");

        System.out.println("sendHtmlEmail: "+helper);

        this.emailSender.send(message);


    }
}
