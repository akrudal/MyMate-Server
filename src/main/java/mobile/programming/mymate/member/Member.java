package mobile.programming.mymate.member;


import lombok.Getter;
import lombok.Setter;
import mobile.programming.mymate.mate.Mate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String userId;

    private String password;

    private String mateType1;

    private String mateType2;

    private Boolean noise;

    private Boolean smoke;

    private String wakeUp;

    private String sleep;

    private String shower;

    private double rate=0.0;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Mate> mates = new HashSet<>();

    public Member(String nickname, String userId, String password) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }

    public Member() {
    }

    public void setDetail(Boolean noise,Boolean smoke, String wakeUp,String sleep,String shower ){
        this.noise=noise;
        this.smoke=smoke;
        this.wakeUp=wakeUp;
        this.sleep=sleep;
        this.shower=shower;
    }
}
