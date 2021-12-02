package mobile.programming.mymate.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SendMessageListResponse {

    private int count;

    private List<MessageDto> data;

}
