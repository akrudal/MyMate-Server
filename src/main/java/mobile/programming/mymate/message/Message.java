package mobile.programming.mymate.message;


import lombok.Getter;
import mobile.programming.mymate.BaseTimeEntity;
import mobile.programming.mymate.member.Member;

import javax.persistence.*;

@Entity
@Getter
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;

    private String context;

    public Message(Member sender, Member receiver, String context) {
        this.sender = sender;
        this.receiver = receiver;
        this.context = context;
    }

    protected Message() {}
}
