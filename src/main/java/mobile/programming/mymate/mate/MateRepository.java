package mobile.programming.mymate.mate;

import mobile.programming.mymate.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MateRepository extends JpaRepository<Mate, Mate.Key> {

    List<Mate> findMatesByUser (Member user);

    List<Mate> findMatesByMember (Member member);

    Optional<Mate> findByUserAndMember (Member user, Member member);


}
