package mobile.programming.mymate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMyResponse {
    private String nickname;

    private String mateType1;

    private String mateType2;

    private double rate;
}
