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
    String jsonString = "{firstName: \"Ivan\","
            + "lastName: \"Ivanov\","
            + "hobby: \"Guitar\","
            + "birthDate: \"13-03-1985\" }";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        json = new Json();
        human = new Human("Ivan", "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));
    }

    @DisplayName("Happy path")
    @org.junit.Test
    public void toJsonTest() throws IllegalAccessException {
        assertEquals(jsonString, json.toJson(human));
    }
}