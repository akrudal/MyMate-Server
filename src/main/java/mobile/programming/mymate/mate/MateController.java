package mobile.programming.mymate.mate;


import lombok.AllArgsConstructor;
import mobile.programming.mymate.mate.dto.ApplyMateListResponse;
import mobile.programming.mymate.mate.dto.CreateMateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/mate")
public class MateController {

    private final MateService mateService;

    @PostMapping
    public ResponseEntity createMate(@RequestBody CreateMateRequest request) {
        mateService.createMate(request);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/apply/{memberId}")
//    public ResponseEntity<ApplyMateListResponse> applyMateList(@PathVariable Long memberId) {
//        return ResponseEntity.ok()
//                .body(mateService.applyMateList(memberId));
//    }

}
