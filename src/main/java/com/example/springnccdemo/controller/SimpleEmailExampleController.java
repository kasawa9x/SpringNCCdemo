package com.example.springnccdemo.controller;

import com.example.springnccdemo.configuration.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class SimpleEmailExampleController {

    @Autowired
    private JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String sendHtmlEmail() throws MessagingException {
        System.out.println("sendHtmlEmail");

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "ffff";

        message.setContent(htmlMsg, "text/html");

        helper.setFrom(MyConstants.MY_EMAIL);

        helper.setTo(MyConstants.FRIEND_EMAIL);

        helper.setSubject("Test send HTML email");

        System.out.println("sendHtmlEmail: "+helper);

        this.emailSender.send(message);

        return "Email Sent!";
    }
}


