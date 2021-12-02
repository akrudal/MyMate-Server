package mobile.programming.mymate.mate;

import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.mate.dto.ApplyMateListResponse;
import mobile.programming.mymate.mate.dto.CreateMateRequest;
import mobile.programming.mymate.member.Member;
import mobile.programming.mymate.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateService {

    private final MateRepository mateRepository;
    private final MemberRepository memberRepository;

    public void createMate(CreateMateRequest request) {

        Optional<Member> userOptional = memberRepository.findById(request.getUserId());
        Member user = userOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Optional<Member> memberOptional = memberRepository.findById(request.getMemberId());
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Mate mate = request.toEntity(user, member);
        mateRepository.save(mate);
    }

//    public ApplyMateListResponse applyMateList(Long memberId) {
//
//        Optional<Member> userOptional = memberRepository.findById(memberId);
//        Member user = userOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
//
//
//
//    }

}
