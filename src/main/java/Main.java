import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human("Ivan", "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));

        Gson gson = new Gson();
        String json1 = gson.toJson(human);

        System.out.println(json1);

        Json service = new Json();
        System.out.println(service.toJson(human));
    }
}
