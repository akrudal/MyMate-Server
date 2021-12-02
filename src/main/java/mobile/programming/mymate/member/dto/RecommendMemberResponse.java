package mobile.programming.mymate.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendMemberResponse {

    private int count;

    private List<MemberDto> data;

}
