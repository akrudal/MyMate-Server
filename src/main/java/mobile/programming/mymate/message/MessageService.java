package mobile.programming.mymate.message;

import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.member.Member;
import mobile.programming.mymate.member.MemberRepository;
import mobile.programming.mymate.message.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public SendMessageResponse sendMessage(SendMessageRequest request) {

        Optional<Member> senderOptional = memberRepository.findById(request.getSenderId());
        Member sender = senderOptional.orElseThrow(IllegalArgumentException::new);

        Optional<Member> receiverOptional = memberRepository.findById(request.getReceiverId());
        Member receiver = receiverOptional.orElseThrow(IllegalArgumentException::new);

        Message message = request.toEntity(sender, receiver);
        Message savedMessage = messageRepository.save(message);

        return SendMessageResponse.from(savedMessage);
    }

    public SendMessageListResponse sendMessageList(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(IllegalArgumentException::new);
        List<Message> messages = messageRepository.findMessagesBySender(member);
        List<MessageDto> collect = messages.stream()
                .map(message -> new MessageDto(message.getId(), message.getReceiver().getNickname(), message.getContext(), message.getCreatedDate()))
                .collect(toList());

        return new SendMessageListResponse(collect.size(), collect);
    }

    public GetMessageListResponse getMessageList(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(IllegalArgumentException::new);
        List<Message> messages = messageRepository.findMessagesByReceiver(member);
        List<MessageDto> collect = messages.stream()
                .map(message -> new MessageDto(message.getId(), message.getSender().getNickname(), message.getContext(),message.getCreatedDate()))
                .collect(toList());

        return new GetMessageListResponse(collect.size(), collect);
    }

    public SendMessageOneResponse sendMessageOne(Long messageId) {

        Optional<Message> messageOptional = messageRepository.findById(messageId);
        Message message = messageOptional.orElseThrow(IllegalArgumentException::new);

        return SendMessageOneResponse.from(message);
    }

    public GetMessageOneResponse getMessageOne(Long messageId) {

        Optional<Message> messageOptional = messageRepository.findById(messageId);
        Message message = messageOptional.orElseThrow(IllegalArgumentException::new);

        return GetMessageOneResponse.from(message);
    }

}
