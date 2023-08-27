package cl.bci.evaluation.controller;


import cl.bci.evaluation.models.Users;
import cl.bci.evaluation.service.UsersService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.regex.Pattern;

@RestController
public class UsersController {

    public static final Pattern EMAIL_VALIDATION =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static final Pattern PASSWORD_VALIDATION =
            Pattern.compile("^(?=.*[A-Z])(?=.*\\d.*\\d)[A-Za-z\\d]{8,12}$");

    UsersService usersService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody Users user){
        try{
            if((user.getEmail() == null || user.getPassword() == null) && (user.getEmail().trim().length() == 0
                    && user.getPassword().trim().length() == 0)){
                return error("Los campos Email y Password son obligatorios. Favor revisar");
            }else {
                if (!EMAIL_VALIDATION.matcher(user.getEmail()).find()) {
                    return error("El formato del email es incorrecto.");
                } else if (!PASSWORD_VALIDATION.matcher(user.getPassword()).find()) {
                    return error("La password ingresada no cumple con los requisitos solicitados. Esta debe contener una mayuscula, dos numeros y el largo debe estar" +
                            "entre 8 a 12 caracteres.");
                }
                return usersService.signUp(user);
            }
        }catch(Exception e){
            return this.errorInternal(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Users user){
        try{
            return usersService.login(user);
        }catch(Exception e){
            return this.errorInternal(e.getMessage());
        }
    }

    @GetMapping("/find-users")
    public ResponseEntity findUser(@RequestParam String email){
        try{
            return usersService.findUser(email);
        }catch(Exception e){
            return this.errorInternal(e.getMessage());
        }
    }

    @NotNull
    public ResponseEntity error(String message){
        String payload = "{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + message + "\"\", \"codigo\":\"\"" + HttpStatus.BAD_REQUEST.value() + "\"\"}";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

    @NotNull
    public ResponseEntity errorInternal(String message){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + message + "\"\", \"codigo\":\"\"" + HttpStatus.INTERNAL_SERVER_ERROR.value() + "\"\"}");
    }
}
