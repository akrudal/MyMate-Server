package mobile.programming.mymate.mate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mobile.programming.mymate.mate.dto.AppliedMateListResponse;
import mobile.programming.mymate.mate.dto.ApplyMateListResponse;
import mobile.programming.mymate.mate.dto.CreateMateRequest;
import mobile.programming.mymate.mate.dto.MateListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/mates")
public class MateController {

    private final MateService mateService;

    // Mate 신청 보내기
    @PostMapping
    public ResponseEntity createMate(@RequestBody CreateMateRequest request) {
        mateService.createMate(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 내가 신청한 Mate List
    @GetMapping("/apply/{memberId}")
    public ResponseEntity<ApplyMateListResponse> applyMateList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(mateService.applyMateList(memberId));
    }

    // 신청 받은 Mate List
    @GetMapping("/applied/{memberId}")
    public ResponseEntity<AppliedMateListResponse> appliedMateList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(mateService.appliedMateList(memberId));
    }

    //이미 신청 한 사람 별점이 -1이면 별점주기 별점이 -1이 아니면 별점 준것
    @GetMapping("/{memberId}")
    public ResponseEntity<MateListResponse> mateList(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(mateService.mateList(memberId));
    }

}
