import org.junit.jupiter.api.*;
import java.util.List;

public class JUnitTests {
    private static long startTime;
    private long testStartTime;

    @BeforeAll
    public static void init(){
        System.out.println("Тестирование запущено");
        startTime = System.nanoTime();
    }
    @AfterAll
    public static void completeTests(){
        System.out.println("Тестирование завершено "+ (System.nanoTime() - startTime));
    }
    @BeforeEach
    public void runningTest(){
        System.out.println("Тест запущен");
        testStartTime = System.nanoTime();
    }
    @AfterEach
    public void finishTests(){
        System.out.println("Тест пройден "+ (System.nanoTime() - testStartTime));
    }

    @org.junit.jupiter.api.Test
    public void TestEmployeeToString() {
        // given:
        String expected = "id: 1 firstName: John lastName: Smith country: USA age: 25";
        // when:
        String result = (new Employee(1, "John", "Smith", "USA", 25)).toString();
        // then:
        Assertions.assertEquals(result,expected);
    }

    @org.junit.jupiter.api.Test
    public void TestGetStrFromJson() {
        // given:
        final String expected = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";
        final String argument = "data.json";
        // when:
        final String result = Main.readString(argument);
        // then:
        Assertions.assertEquals(result, expected);
    }

    @org.junit.jupiter.api.Test
    public void TestObjectCreation() {
        // given:
        final Employee[] expected = {new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)};
        final String argument = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";
        // when:
        final List<Employee> list = Main.jsonToList(argument);
        Employee[] array = new Employee[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i]= list.get(i);
        }
        final Employee[] result = array;
        // then:
        Assertions.assertTrue(Employee.ArraysEquals(result, expected));
    }



}
