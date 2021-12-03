package mobile.programming.mymate.mate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mobile.programming.mymate.member.Member;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public class Mate {

    @EmbeddedId
    private Key key = new Key();

    //신청한 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private Member user;

    //신청 받은 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    private Member member;

    private Boolean isAccept;

    @Setter
    private double rate = -1;

    public void setIsAccept(Boolean isAccept) {
        this.isAccept = isAccept;
        user.getMates().add(this);
        member.getMates().add(this);
    }

    @Builder
    public Mate(Member user, Member member, Boolean isAccept) {
        this.user = user;
        this.member = member;
        this.isAccept = isAccept;
    }

    protected Mate() {}

    @Embeddable
    @AllArgsConstructor
    @Getter
    public static class Key implements Serializable {
        private Long userId;
        private Long memberId;

        protected Key() {}
    }
}
