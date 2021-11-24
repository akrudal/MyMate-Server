package mobile.programming.mymate.member;


import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.member.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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


        memberOptional.get().setMateType(request.getMateType());
        return new CreateTypeResponse(memberOptional.get().getMateType());
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
        return new CreateMyResponse(my.getNickname(),my.getMateType(),my.getRate());
    }

    public CreateDetailResponse getDetail(Long id){
        Optional<Member> memberOptional=memberRepository.findMemberById(id);
        if(memberOptional.isEmpty()){
            throw new IllegalStateException("회원 정보가 없습니다.");
        }

        Member detail=memberOptional.get();
        return new CreateDetailResponse(detail.getNoise(),detail.getSmoke(),detail.getWakeUp(),detail.getSleep(),detail.getShower());
    }

}
