package mobile.programming.mymate.message.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.message.Message;

@Getter
@AllArgsConstructor
public class SendMessageResponse {

    private Long id;

    public static SendMessageResponse from(Message message) {
        return new SendMessageResponse(message.getId());
    }

}
