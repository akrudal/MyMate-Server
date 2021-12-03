package mobile.programming.mymate.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AppliedMateListResponse {

    private int count;

    private List<MateDto> data;
}
