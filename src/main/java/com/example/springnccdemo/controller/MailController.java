package com.example.springnccdemo.controller;

import com.example.springnccdemo.configuration.MyConstants;
import com.example.springnccdemo.model.Bill;
import com.example.springnccdemo.model.User;
import com.example.springnccdemo.repository.UserRepository;
import com.example.springnccdemo.service.BillDetailService;
import com.example.springnccdemo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;

@Controller
public class MailController {

    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private BillDetailService billDetailService;


    @RequestMapping("/sendMail")
    public String sendMail(Principal principal) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        User user = userRepository.findUserByEmail(principal.getName()).get();

        String htmlMsg =  " <h3>Hi " + user.getFirstName()+" "+user.getLastName() + " , the order you placed was successful !!! </h3> "
                + "<img src='https://shopta.vn/images/2015/11/dat-hang-thanh-cong.jpg'>";

        message.setContent(htmlMsg, "text/html");

//        FileSystemResource file = new FileSystemResource(new File("test.txt"));
//        helper.addAttachment("Demo Mail", file);
        helper.setFrom(MyConstants.MY_EMAIL);
        helper.setTo(user.getEmail());

        helper.setSubject( "ORDER NOTICE" );

        this.javaMailSender.send(message);
        return "redirect:/";
    }

}