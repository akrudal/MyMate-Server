package mobile.programming.mymate.member;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

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

    private String mateType;

    private Boolean noise;

    private Boolean smoke;

    private String wakeUp;

    private String sleep;

    private String shower;

    private double rate=0.0;

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
