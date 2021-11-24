package mobile.programming.mymate.mate;

import lombok.Getter;
import mobile.programming.mymate.member.Member;

import javax.persistence.*;

@Entity
@Getter
public class Mate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

}
