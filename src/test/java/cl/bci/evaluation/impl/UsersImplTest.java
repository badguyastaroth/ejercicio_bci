package cl.bci.evaluation.impl;

import cl.bci.evaluation.models.Users;
import cl.bci.evaluation.repository.PhoneRepository;
import cl.bci.evaluation.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersImplTest {

    @InjectMocks
    private UsersImpl userImpl = new UsersImpl();

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void jwtTokenTest(){
        org.springframework.test.util.ReflectionTestUtils.setField(
                userImpl,
                "secret",
                "8x7hgiS!85_iS^zyu7e<ygjeui%IdBJdshkqw:,mv¡>*");
        assertNotNull(userImpl.getJWTToken("f.guerraaraya@gmail.com"));
    }

    @Test
    public void signUpTest(){
        Users users = new Users();
        users.setEmail("test@test.cl");
        users.setPassword("test");
        assertNotNull(userImpl.signUp(users));
    }

    @Test
    public void loginTest(){
        Users users = new Users();
        users.setEmail("test@test.cl");
        users.setPassword("test");
        assertNotNull(userImpl.login(users));
    }

    @Test
    public void findUserTest() throws JsonProcessingException {
        assertNotNull(userImpl.findUser("test@test.cl"));
    }

    @Test
    public void findUserTest2() throws JsonProcessingException {
        assertNotNull(userImpl.findUser("f.guerraaraya@gmail.com"));
    }

}
