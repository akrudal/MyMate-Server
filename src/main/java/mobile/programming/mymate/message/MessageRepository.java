package mobile.programming.mymate.message;

import mobile.programming.mymate.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessagesBySender(Member member);

    List<Message> findMessagesByReceiver(Member member);

}
