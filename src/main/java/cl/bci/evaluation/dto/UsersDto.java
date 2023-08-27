package cl.bci.evaluation.dto;

import cl.bci.evaluation.models.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto{

    private String id;
    private String name;
    private String email;
    private String password;
    private String created;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private List<Phone> phoneList;
}
