package mobile.programming.mymate.member;


import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.member.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp") // http://localhost:8080/member
    public CreateSignUpResponse createSignUp(@RequestBody CreateSignUpRequest request) {
        return memberService.createSignUp(request);
    }

    @PostMapping("/login")
    public CreateLoginResponse createLogin(@RequestBody CreateLoginRequest request){
        return memberService.createLogin(request);
    }

    @PostMapping("/type")
    public CreateTypeResponse createType(@RequestBody CreateTypeRequest request){
        return memberService.createType(request);
    }

    //코골이 + 흡연 + ... + 씻는 시간대 => 수정 버튼 click시 Post한 내용을 save
    @PostMapping("/detail")
    public ResponseEntity createDetail(@RequestBody CreateDetailRequest request){
        memberService.createDetail(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    //닉네임 + 성향 + 평점 => 수정 불가한 부분
    @GetMapping("/my/{id}")
    public CreateMyResponse getMy(@PathVariable Long id){

        return memberService.getMy(id);
    }

    //코골이 + 흡연 + .. + 씻는 시간대 => 수정 가능한 부분 => Get+Post 둘 다 사용
    @GetMapping("/detail/{id}")
    public CreateDetailResponse getDetail(@PathVariable Long id){

        return memberService.getDetail(id);
    }

    //나에게 딱 맞는 MATE 조회
    @GetMapping("/recommend/{mateType1}")
    public ResponseEntity<RecommendMemberResponse> recommendList(@PathVariable String mateType1) {
        return ResponseEntity.ok()
                .body(memberService.recommendList(mateType1));
    }

    //예전 MATE와 비슷한 MATE
    @GetMapping("/similar/{mateType1}/{mateType2}")
    public ResponseEntity<SimilarMemberResponse> similarList(@PathVariable String mateType1, @PathVariable String mateType2) {
        return ResponseEntity.ok()
                .body(memberService.similarList(mateType1,mateType2));
    }

    // 다른 회원 상세 보기
    @GetMapping("/other/{memberId}")
    public ResponseEntity<OtherMemberResponse> otherMember(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(memberService.otherMember(memberId));
    }

}
