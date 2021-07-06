package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.scheduler.EmailScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailScheduler emailScheduler;

    @Test
    public void shouldSendEmail() {
        //given
        //Mail mail = new Mail("test@test.com", "Test", "Test message");
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .subject("Test")
                .message("Test message")
                .toCc(null)
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //when
        simpleEmailService.sendSimpleMail(mail);

        //then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    void sendInformationEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "test","test_message",null);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());
        //When
        doNothing().doThrow(new RuntimeException()).when(emailScheduler).sendInformationEmail();
        emailScheduler.sendInformationEmail();
        simpleEmailService.send(mail);
        //Then
        verify(emailScheduler, times(1)).sendInformationEmail();
    }
}