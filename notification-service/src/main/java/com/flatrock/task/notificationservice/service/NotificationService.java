package com.flatrock.task.notificationservice.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;

@Service
public class NotificationService {
    private final SendGrid sendGrid;

    @Value("${notification-service.sms-sender}")
    private String from;

    public NotificationService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public String sendNotification(String email, String subject, String context) {
        Email from = new Email("qartlelishvilioto@gmail.com");
        Email to = new Email(email);
        Content content = new Content("text/plain", context);
        Mail mail = new Mail(from, subject, to, content);
        mail.setReplyTo(new Email("qartlelishvilioto@gmail.com"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);
            return "message sent";
        } catch (IOException ex) {
            throw new InternalServerErrorException("error sending email");
        }
    }

    public String sendSMS(String to, String context) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(from),
                        context)
                .create();
        return message.getAccountSid();
    }
}
