package mobile.programming.mymate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.member.Member;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String nickname;

    private String mateType1;

    private String mateType2;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getId(), member.getNickname(), member.getMateType1(), member.getMateType2());
    }

}
