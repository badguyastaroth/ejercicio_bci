package cl.bci.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.List;

@Entity(name="USERS")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Column(name="id")
    @Id
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="created")
    private Instant created;

    @Column(name="last_login")
    private Instant lastLogin;

    @Column(name="token")
    private String token;

    @Column(name="is_active")
    private Integer isActive;

    @Transient
    private List<Phone> phoneList;

}
