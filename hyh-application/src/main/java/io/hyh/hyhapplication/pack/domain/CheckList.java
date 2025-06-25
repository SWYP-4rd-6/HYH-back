package io.hyh.hyhapplication.pack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_list")
@Getter
@NoArgsConstructor
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CheckUserTest user;

    @Column(name = "checking")
    private int check;

    @Column(name = "thing")
    private String thing;

    public CheckList(CheckUserTest user, int check, String thing) {
        this.user = user;
        this.check = check;
        this.thing = thing;
    }

    public void setUser(CheckUserTest user) {
        this.user = user;
        user.getCheckLists().add(this);
    }

    public void setCheck() {
        switch (this.check) {
            case 0 -> this.check = 1;
            case 1 -> this.check = 0;
        }
    }
}
