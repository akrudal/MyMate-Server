package mobile.programming.mymate.mate.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.mate.Mate;
import mobile.programming.mymate.member.Member;

@Getter
@AllArgsConstructor
public class CreateMateRequest {

    private Long userId;

    private Long memberId;

    public Mate toEntity(Member user, Member member) {
        return new Mate(user, member, false);
    }

}
