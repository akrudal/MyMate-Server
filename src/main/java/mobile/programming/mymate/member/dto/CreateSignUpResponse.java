package mobile.programming.mymate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateSignUpResponse {

    private Long id;

    private String nickname;

    private String userId;

}
