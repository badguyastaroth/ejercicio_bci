package cl.bci.evaluation.impl;


import cl.bci.evaluation.dto.UsersDto;
import cl.bci.evaluation.models.Phone;
import cl.bci.evaluation.models.Users;
import cl.bci.evaluation.repository.PhoneRepository;
import cl.bci.evaluation.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersImpl {

    @Value("${jwt.secret}")
    String secret;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PhoneRepository phoneRepository;

    public ResponseEntity signUp(Users user) {
        if(usersRepository.findUserByEmail(user.getEmail()) > 0){
            return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + "El usuario ingresado ya existe" + "\"\", \"codigo\":\"\"" + HttpStatus.OK.value() + "\"\"}");
        }else{
            UUID uuid = UUID.randomUUID();
            usersRepository.insertUser(uuid.toString(), user.getEmail(), user.getPassword(), Instant.now());
            if(user.getPhoneList() != null && user.getPhoneList().size() > 0){
                for(Phone p : user.getPhoneList()){
                    phoneRepository.insertPhone(p.getNumber(), p.getCityCode(), p.getCountryCode(), uuid.toString());
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + "Usuario Creado Exitosamente" + "\"\", \"codigo\":\"\"" + HttpStatus.CREATED.value() + "\"\"}");
        }
    }

    public ResponseEntity login(Users user) {
        if(usersRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == 0){
            return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + "El usuario no existe" + "\"\", \"codigo\":\"\"" + HttpStatus.OK.value() + "\"\"}");
        }else{
            String token = getJWTToken(user.getEmail());
            usersRepository.updateUser(token.replace("Bearer",""), Instant.now(), user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + token + "\"\", \"codigo\":\"\"" + HttpStatus.OK.value() + "\"\"}");
        }
    }

    public ResponseEntity findUser(String email) throws JsonProcessingException {
        if(usersRepository.findUserByEmail(email) == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + "El usuario no existe" + "\"\", \"codigo\":\"\"" + HttpStatus.OK.value() + "\"\"}");
        }else{
            Users user = usersRepository.findUser(email);
            List<Phone> list = phoneRepository.findAllByUserId(user.getId());
            UsersDto userDto = new UsersDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setCreated(user.getCreated().toString());
            userDto.setLastLogin(user.getLastLogin().toString());
            userDto.setToken(user.getToken());
            userDto.setIsActive(user.getIsActive() == 1 ? true : false);
            userDto.setPhoneList(list);
            String serialized = new ObjectMapper().writeValueAsString(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\", \"mensaje\":\"\"" + serialized + "\"\", \"codigo\":\"\"" + HttpStatus.OK.value() + "\"\"}");
        }
    }

    public String getJWTToken(String email) {
        String secretKey = secret;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder()
                .setSubject(email)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(getSigningKey())
                .compact();
        return "Bearer " + token;
    }

    private Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
