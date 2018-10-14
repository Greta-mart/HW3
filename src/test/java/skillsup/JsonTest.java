package skillsup;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    private Json json;
    private Human human;

    @Before
    public void init() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        json = new Json();
        human = new Human("Ivan", "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));

    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test1_toJson() throws IllegalAccessException {
        String expected = json.toJson(human);
        assertEquals(expected, json.toJson(human));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test2_toJson() throws IllegalAccessException {
        human = new Human(null, "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));
        String expected = json.toJson(human);
        assertEquals(expected, json.toJson(human));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test3_toJson() throws IllegalAccessException {
        human = new Human(null, null, null,
                null);
        String expected = json.toJson(human);
        assertEquals(expected, json.toJson(human));
    }

}