package skillsup;

import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human("Ivan", "Ivanov", "Guitar",
                LocalDate.of(1985, Month.MARCH, 13));

        Gson gson = new Gson();
        String json = gson.toJson(human);

        System.out.println(json);

        //google json
        Json service = new Json();
        System.out.println(service.toJson(human));

        //my realization json
        Json_second serv = new Json_second();
        System.out.println(serv.toJson1(human));

    }
}
