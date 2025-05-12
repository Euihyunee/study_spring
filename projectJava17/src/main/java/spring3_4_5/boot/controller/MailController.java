package spring3_4_5.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring3_4_5.boot.dto.MailDTO;
import spring3_4_5.boot.service.impl.MailServiceImpl;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl mailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody MailDTO mailDTO) {
        mailService.sendSimpleMessage(mailDTO);
        return "mail sent successfully";
    }
}
