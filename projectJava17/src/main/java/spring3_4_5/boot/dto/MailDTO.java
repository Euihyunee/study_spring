package spring3_4_5.boot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MailDTO {

    private String to;
    private String subject;
    private String text;
}
