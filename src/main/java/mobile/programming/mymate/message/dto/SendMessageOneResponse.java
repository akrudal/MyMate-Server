package mobile.programming.mymate.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.message.Message;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SendMessageOneResponse {

    private String nickname;

    private String context;

    private LocalDateTime time;

    public static SendMessageOneResponse from(Message message) {
        return new SendMessageOneResponse(message.getReceiver().getNickname(), message.getContext(), message.getCreatedDate());
    }

}
