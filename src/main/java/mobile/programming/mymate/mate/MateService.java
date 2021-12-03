package mobile.programming.mymate.mate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mobile.programming.mymate.mate.dto.*;
import mobile.programming.mymate.member.Member;
import mobile.programming.mymate.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class MateService {

    private final MateRepository mateRepository;
    private final MemberRepository memberRepository;

    public void createMate(CreateMateRequest request) {

        Optional<Member> userOptional = memberRepository.findById(request.getUserId());
        Member user = userOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Optional<Member> memberOptional = memberRepository.findById(request.getMemberId());
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Optional<Mate> user1 = mateRepository.findByUserAndMember(user, member);
        Optional<Mate> user2 = mateRepository.findByUserAndMember(member, user);

        if (user1.isPresent() || user2.isPresent()) {
            throw new IllegalStateException("이미 존재하는 MATE입니다.");
        }

        Mate mate = request.toEntity(user, member);
        mateRepository.save(mate);
    }

    public ApplyMateListResponse applyMateList(Long memberId) {
        Optional<Member> userOptional = memberRepository.findById(memberId);
        Member user = userOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        List<Mate> mates = mateRepository.findMatesByUser(user);
        List<MateDto> collect = mates.stream()
                .filter((mate) -> !mate.getIsAccept())
                .map(mate -> MateDto.from(mate.getMember(), mate))
                .collect(toList());

        return new ApplyMateListResponse(collect.size(), collect);
    }

    public AppliedMateListResponse appliedMateList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        List<Mate> mates = mateRepository.findMatesByMember(member);
        List<MateDto> collect = mates.stream()
                .filter((mate) -> !mate.getIsAccept())
                .map(mate -> MateDto.from(mate.getUser(), mate))
                .collect(toList());

        return new AppliedMateListResponse(collect.size(), collect);
    }

    public MateListResponse mateList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findMemberByIdWithJoin(memberId);
        Member member = memberOptional.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<Mate> mates = member.getMates()
                .stream()
                .filter(Mate::getIsAccept)
                .collect(toList());

        List<MateMember> temp = new ArrayList<>();

        for (Mate mate : mates) {
            if(mate.getMember().getId().equals(member.getId())) {
                temp.add(new MateMember(mate, mate.getUser()));
            } else if (mate.getUser().getId().equals(member.getId())) {
                temp.add(new MateMember(mate, mate.getMember()));
            }
        }

        List<MateDto> collect = temp.stream()
                .map(t -> MateDto.from(t.getMember(), t.getMate()))
                .collect(toList());

        return new MateListResponse(collect.size(), collect);
    }

    @Getter
    @AllArgsConstructor
    static class MateMember {
        private Mate mate;
        private Member member;
    }

}
