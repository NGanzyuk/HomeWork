package i18n;
import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLocalizationServiceImpl {
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
    @Test
    public void RULocalization(){
         final String expected = "Добро пожаловать";
         final LocalizationService localizationService = new LocalizationServiceImpl();
         final String result = localizationService.locale(Country.RUSSIA);
         Assertions.assertEquals(result, expected);
    }
    @Test
    public void ENLocalization(){
        String[] expectedArray = new String[Country.values().length - 1];
        for (int i = 0; i < expectedArray.length; i++) {
            expectedArray[i] = "Welcome";
        }
        final String[] expected = expectedArray;
        final LocalizationService localizationService = new LocalizationServiceImpl();
        List<String> resultArray = new ArrayList<>();

        for (Country country: Country.values()) {
            if (country != Country.RUSSIA){
                resultArray.add(localizationService.locale(country));
            }
        }
        final List<String> result = resultArray;
        Assertions.assertEquals(result.toString(),Arrays.toString(expected));
    }
}
