package geo;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;


public class TestLocationbyIP {
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
    public void LocationByIPTest (){
        final Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoService geoService = new GeoServiceImpl();
        final Location result = geoService.byIp("172.123.12.19");
        Assertions.assertEquals(result.toString(), expected.toString());
    }
}
