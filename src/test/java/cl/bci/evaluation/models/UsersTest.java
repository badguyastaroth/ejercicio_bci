package cl.bci.evaluation.models;

import cl.bci.dto.UsersDto;
import cl.bci.models.Phone;
import cl.bci.models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsersTest {

    @Test
    public void usersDtoTest() throws Exception {
        Users actual = new Users();
        Users expect = new Users();
        List<Phone> list = new ArrayList<>();
        Instant i = Instant.now();
        actual.setId("test-test-test");
        actual.setName("Felipe");
        actual.setEmail("f.guerraaraya@gmail.com");
        actual.setPassword("estoesunapassword");
        actual.setCreated(i);
        actual.setLastLogin(i);
        actual.setToken("sdsfghjkk");
        actual.setIsActive(1);
        actual.setPhoneList(list);

        expect.setId("test-test-test");
        expect.setName("Felipe");
        expect.setEmail("f.guerraaraya@gmail.com");
        expect.setPassword("estoesunapassword");
        expect.setCreated(i);
        expect.setLastLogin(i);
        expect.setToken("sdsfghjkk");
        expect.setIsActive(1);
        expect.setPhoneList(list);

        assertEquals(expect.equals(actual), actual.equals(expect));
    }
}
