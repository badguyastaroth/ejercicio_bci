package cl.bci.evaluation.service;

import cl.bci.evaluation.models.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    ResponseEntity signUp(Users user);
    ResponseEntity login(Users user);
    ResponseEntity findUser(String email) throws JsonProcessingException;
}
