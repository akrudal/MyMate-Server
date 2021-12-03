package mobile.programming.mymate.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.mate.Mate;
import mobile.programming.mymate.member.Member;

@Getter
@AllArgsConstructor
public class MateDto {

    private Long memberId;

    private String nickname;

    private String mateType1;

    private String mateType2;

    private double rate;

    public static MateDto from(Member member, Mate mate) {
        return new MateDto(member.getId(), member.getNickname(), member.getMateType1(), member.getMateType2(), mate.getRate());
    }

}
