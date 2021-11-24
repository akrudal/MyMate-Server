package mobile.programming.mymate.member.dto;

import lombok.Getter;

@Getter
public class CreateDetailRequest {
    private Long id;
    private Boolean noise;
    private Boolean smoke;
    private String wakeUp;
    private String sleep;
    private String shower;
}