package mobile.programming.mymate.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDetailResponse {
    private Boolean noise;
    private Boolean smoke;
    private String wakeUp;
    private String sleep;
    private String shower;
}
