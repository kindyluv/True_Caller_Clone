package com.precious.truecaller.service.messageService.email;

import com.precious.truecaller.data.dto.request.EmailMessageRequest;
import com.precious.truecaller.service.messageService.email.GeneralMessageNotification;
import com.precious.truecaller.web.exception.BusinessLogicException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailSenderImpl implements GeneralMessageNotification {

    private static final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    private final static String USERNAME = "";
    private final static String PASSWORD = "";

    @Override
    public void sendMessageNotification(EmailMessageRequest request) {
        Properties mail = javaMailSender.getJavaMailProperties();
        mail.put("mail.transport.protocol", "smtp");
        mail.put("mail.smtp.auth", "true");
        mail.put("mail.smtp.starttls.enable", "true");
        mail.put("mail.smtp.starttls.required", "true");
        mail.put("mail.debug", "true");
        mail.put("mail.smtp.ssl.enable", "true");
        mail.put("mail.host", "smtp.gmail.com");
        mail.put("mail.port", 465);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(request.getReceiverEmail());
            mimeMessageHelper.setFrom(request.getSenderEmail());
            mimeMessageHelper.setText(request.getMessage(), true);
            mimeMessageHelper.setSubject(request.getSubject());
        } catch (MessagingException e) {
            throw new BusinessLogicException(e.getMessage());
        }

        javaMailSender.setUsername(USERNAME);
        javaMailSender.setPassword(PASSWORD);
        javaMailSender.send(mimeMessage);
    }
}
