package cl.bci.evaluation.controller;

import cl.bci.controller.UsersController;
import cl.bci.impl.UsersImpl;
import cl.bci.models.Users;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersControllerTest {

    @InjectMocks
    private UsersController controller = new UsersController();

    @Test
    public void signUpTest(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword(null);
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpTest1(){
        Users user = new Users();
        user.setEmail("test@test.cl");
        user.setPassword("test");
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpTest2(){
        Users user = new Users();
        user.setEmail("testtest.cl");
        user.setPassword("Test12dsfg");
        assertNotNull(controller.signUp(user));
    }

    @Test
    public void signUpTest3(){
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
