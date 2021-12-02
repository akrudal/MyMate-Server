package mobile.programming.mymate.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUserId(String userId);

    Optional<Member> findMemberById(Long id);

    Optional<Member> findMemberByUserIdAndPassword(String userId,String password);

    List<Member> findMembersByMateType1(String mateType1);

    List<Member> findMembersByMateType1AndMateType2(String mateType1, String mateType2);
}
