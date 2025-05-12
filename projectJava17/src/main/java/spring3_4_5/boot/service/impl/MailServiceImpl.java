package spring3_4_5.boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import spring3_4_5.boot.dto.MailDTO;

@Service
@RequiredArgsConstructor
public class MailServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lobil7782@gmail.com");
        message.setTo(mailDTO.getTo());
        message.setSubject(mailDTO.getSubject());
        message.setText(mailDTO.getText());
        javaMailSender.send(message);
    }
}
