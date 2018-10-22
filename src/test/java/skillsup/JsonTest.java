package skillsup;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    private Json json;
    private Human human;
    private Json_second json_second;

    @Before
    public void init() {
        json = new Json();
        json_second = new Json_second();
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test1_toJson() throws IllegalAccessException {
        String str = "{\"firstName\":\"Ivan\","
                + "\"lastName\":\"Ivanov\","
                + "\"fun\":\"Guitar\","
                + "\"birthDate\":\"13-03-1985\"}";
        human = new Human("Ivan", "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));
        assertEquals(str, json.toJson(human));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test2_toJson() throws IllegalAccessException {
        human = new Human(null, "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));
        String str = "{\"lastName\":\"Ivanov\","
                + "\"fun\":\"Guitar\","
                + "\"birthDate\":\"13-03-1985\"}";
        assertEquals(str, json.toJson(human));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test3_toJson() throws IllegalAccessException {
        human = new Human(null, null, null,
                null);
        String str = "{}";
        String expected = json.toJson(human);
        assertEquals(expected, json.toJson(human));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void test4_toJson() throws IllegalAccessException {
        human = new Human(null, null, null,
                null);
        String str = "{}";
        String expected = json_second.toJson1(human);
        assertEquals(expected, json_second.toJson1(human));
    }
}