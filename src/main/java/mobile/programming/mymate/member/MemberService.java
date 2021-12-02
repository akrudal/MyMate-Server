package mobile.programming.mymate.member;


import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.member.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public CreateSignUpResponse createSignUp(CreateSignUpRequest request) {

        Optional<Member> memberOptional = memberRepository.findMemberByUserId(request.getUserId());
        if(memberOptional.isPresent()) {
            throw new IllegalStateException("중복되는 회원입니다.");
        }

        Member member = new Member(request.getNickname(), request.getUserId(), request.getPassword());
        Member savedMember = memberRepository.save(member);

        return new CreateSignUpResponse(savedMember.getId(), savedMember.getNickname(), savedMember.getUserId());
    }

    public CreateLoginResponse createLogin(CreateLoginRequest request){
        Optional<Member> memberOptional=memberRepository.findMemberByUserIdAndPassword(request.getUserId(),request.getPassword());
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("일치하는 ID 또는 비밀번호가 없습니다.");
        }


        return new CreateLoginResponse(memberOptional.get().getId());
    }

    public CreateTypeResponse createType(CreateTypeRequest request){
        Optional<Member> memberOptional=memberRepository.findMemberById(request.getId());
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("회원 정보가 없습니다.");
        }

        Member member = memberOptional.get();

        member.setMateType1(request.getMateType1());
        member.setMateType2(request.getMateType2());

        return new CreateTypeResponse(member.getMateType1(), member.getMateType2());
    }

    public void createDetail(CreateDetailRequest request){
        Optional<Member> memberOptional=memberRepository.findMemberById(request.getId());
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("회원 정보가 없습니다.");
        }

        memberOptional.get().setDetail(request.getNoise(),request.getSmoke(),request.getWakeUp(),request.getSleep(),request.getShower());
    }

    public CreateMyResponse getMy(Long id){
        Optional<Member> memberOptional=memberRepository.findMemberById(id);
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("회원 정보가 없습니다.");
        }

        Member my=memberOptional.get();
        return new CreateMyResponse(my.getNickname(),my.getMateType1(),my.getMateType2(),my.getRate());
    }

    public CreateDetailResponse getDetail(Long id){
        Optional<Member> memberOptional=memberRepository.findMemberById(id);
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("회원 정보가 없습니다.");
        }

        Member detail=memberOptional.get();
        return new CreateDetailResponse(detail.getNoise(),detail.getSmoke(),detail.getWakeUp(),detail.getSleep(),detail.getShower());
    }

    public RecommendMemberResponse recommendList(String mateType1) {
        List<Member> members = memberRepository.findMembersByMateType1(mateType1);
        List<MemberDto> collect = members.stream()
                .map(MemberDto::from)
                .collect(toList());
        return new RecommendMemberResponse(collect.size(), collect);
    }

    public SimilarMemberResponse similarList(String mateType1, String mateType2) {
        List<Member> members = memberRepository.findMembersByMateType1AndMateType2(mateType1, mateType2);
        List<MemberDto> collect = members.stream()
                .map(MemberDto::from)
                .collect(toList());

        return new SimilarMemberResponse(collect.size(), collect);
    }

    public OtherMemberResponse otherMember(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        return OtherMemberResponse.from(member);
    }

}
