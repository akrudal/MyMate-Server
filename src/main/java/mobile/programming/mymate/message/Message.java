package mobile.programming.mymate.message;


import lombok.Getter;
import mobile.programming.mymate.member.Member;

import javax.persistence.*;

@Entity
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;

    private String context;

}
