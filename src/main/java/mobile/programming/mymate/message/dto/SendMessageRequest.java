package mobile.programming.mymate.message.dto;

import lombok.Getter;
import mobile.programming.mymate.member.Member;
import mobile.programming.mymate.message.Message;

@Getter
public class SendMessageRequest {

    private Long senderId;

    private Long receiverId;

    private String context;

    public Message toEntity(Member sender, Member receiver) {
        return new Message(sender, receiver, this.context);
    }

}
