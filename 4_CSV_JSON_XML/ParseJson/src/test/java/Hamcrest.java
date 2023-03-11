import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hamcrest.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class Hamcrest {
    @Test
    public void TestEmployeeToString() {
        // given:
        String expected = "id: 1 firstName: John lastName: Smith country: USA age: 25";
        // when:
        String result = (new Employee(1, "John", "Smith", "USA", 25)).toString();
        // then:
        assertThat(result, equalTo(expected));
    }
    @Test
    public void TestGetStrFromJson() {
        // given:
        final String expected = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";
        final String argument = "data.json";
        // when:
        final String result = Main.readString(argument);
        // then:
        assertThat(result, equalTo(expected));
    }
    @Test
    public void TestObjectCreation() {
        // given:
        final String[] expected = {(new Employee(1, "John", "Smith", "USA", 25)).toString(),
                (new Employee(2, "Ivan", "Petrov", "RU", 23)).toString()};
        final String argument = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";
        // when:
        final List<Employee> list = Main.jsonToList(argument);
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i]= list.get(i).toString();
        }
        final String[] result = array;
        // then:
        assertThat(result, equalTo(expected));
    }
}
