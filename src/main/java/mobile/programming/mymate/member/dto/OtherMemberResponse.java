package mobile.programming.mymate.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.member.Member;

@Getter
@AllArgsConstructor
public class OtherMemberResponse {

    private Long id;

    private String nickname;

    private String mateType1;

    private String mateType2;

    private Boolean noise;

    private Boolean smoke;

    private String wakeUp;

    private String sleep;

    private String shower;

    private double rate;

    public static OtherMemberResponse from(Member member) {
        return new OtherMemberResponse(member.getId(),
                member.getNickname(),
                member.getMateType1(),
                member.getMateType2(),
                member.getNoise(),
                member.getSmoke(),
                member.getWakeUp(),
                member.getSleep(),
                member.getShower(),
                member.getRate());
    }
}
