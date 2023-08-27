package cl.bci.evaluation.dto;

import cl.bci.evaluation.models.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsersDtoTest {

    @Test
    public void usersDtoTest()  {
        UsersDto actual = new UsersDto();
        UsersDto expect = new UsersDto();
        List<Phone> list = new ArrayList<>();
        Instant i = Instant.now();
        actual.setId("test-test-test");
        actual.setName("Felipe");
        actual.setEmail("f.guerraaraya@gmail.com");
        actual.setPassword("estoesunapassword");
        actual.setCreated(i.toString());
        actual.setLastLogin(i.toString());
        actual.setToken("sdsfghjkk");
        actual.setIsActive(true);
        actual.setPhoneList(list);

        expect.setId("test-test-test");
        expect.setName("Felipe");
        expect.setEmail("f.guerraaraya@gmail.com");
        expect.setPassword("estoesunapassword");
        expect.setCreated(i.toString());
        expect.setLastLogin(i.toString());
        expect.setToken("sdsfghjkk");
        expect.setIsActive(true);
        expect.setPhoneList(list);

        assertEquals(expect.equals(actual), actual.equals(expect));
    }
}
