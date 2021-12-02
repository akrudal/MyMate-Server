package mobile.programming.mymate.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.message.Message;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetMessageOneResponse {

    private String nickname;

    private String context;

    private LocalDateTime time;

    public static GetMessageOneResponse from(Message message) {
        return new GetMessageOneResponse(message.getSender().getNickname(), message.getContext(), message.getCreatedDate());
    }

}
