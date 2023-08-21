package cl.bci.evaluation.models;

import cl.bci.models.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PhoneTest {

    @Test
    public void usersDtoTest() throws Exception {
        Phone actual = new Phone();
        Phone expect = new Phone();
        List<Phone> list = new ArrayList<>();
        Instant i = Instant.now();
        actual.setIdPhone(1L);
        actual.setNumber(999999999L);
        actual.setCityCode(32);
        actual.setCountryCode("CL");
        actual.setId("test-test-test");

        expect.setIdPhone(1L);
        expect.setNumber(999999999L);
        expect.setCityCode(32);
        expect.setCountryCode("CL");
        expect.setId("test-test-test");

        assertEquals(expect.equals(actual), actual.equals(expect));
    }
}
