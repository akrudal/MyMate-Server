package mobile.programming.mymate.message;

import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.message.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    // 쪽지 보내기
    @PostMapping
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody SendMessageRequest request) {
        return ResponseEntity.ok()
                    .body(messageService.sendMessage(request));
    }

    // 내가 보낸 쪽지함
    @GetMapping("/bySender/{memberId}")
    public ResponseEntity<SendMessageListResponse> sendMessageList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(messageService.sendMessageList(memberId));
    }

    // 내가 받은 쪽지함
    @GetMapping("/byReceiver/{memberId}")
    public ResponseEntity<GetMessageListResponse> getMessageList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(messageService.getMessageList(memberId));
    }

    // 내가 받은 쪽지함 상세 메세지
    @GetMapping("/get/{messageId}")
    public ResponseEntity<GetMessageOneResponse> getMessageOne(@PathVariable Long messageId) {
        return ResponseEntity.ok()
                .body(messageService.getMessageOne(messageId));
    }

    // 내가 보낸 쪽지함 상세 메세지
    @GetMapping("/send/{messageId}")
    public ResponseEntity<SendMessageOneResponse> sendMessageOne(@PathVariable Long messageId) {
        return ResponseEntity.ok()
                .body(messageService.sendMessageOne(messageId));
    }

}
