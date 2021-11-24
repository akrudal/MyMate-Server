package mobile.programming.mymate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMyResponse {
    private String nickname;

    private String mateType;

    private double rate;
}
