package cl.bci.evaluation.controller;

import cl.bci.evaluation.models.Users;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersControllerTest {

    @InjectMocks
    private UsersController controller = new UsersController();

    @Test
    public void signUpMissingPasswordTest(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword(null);
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpErrorPasswordTest(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword("test");
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpErrorFormatEmailTest(){
        Users user = new Users();
        user.setEmail("testtest.cl");
        user.setPassword("Test12dsfg");
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpCorrectTest(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword("Test12dsfg");
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void loginTest(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword("test");
        assertNotNull(controller.login(user));
    }

    @Test
    public void findUserTest(){
        assertNotNull(controller.findUser("f.guerraaraya@gmail.com"));
    }

    @Test
    public void errorTest(){
        assertNotNull(controller.error("Usuario no encontrado"));
    }

    @Test
    public void internalErrorTest(){
        assertNotNull( controller.errorInternal("Ha ocurrido un error"));
    }
}
