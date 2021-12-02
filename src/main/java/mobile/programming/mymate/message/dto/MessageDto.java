package mobile.programming.mymate.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private String nickname;

    private String context;

    private LocalDateTime time;

}
