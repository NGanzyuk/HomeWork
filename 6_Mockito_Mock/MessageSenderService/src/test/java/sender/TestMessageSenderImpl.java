package sender;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;


public class TestMessageSenderImpl {
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
    public void testSendRUText(){
        final String expected = "Добро пожаловать";
        final Country country = Country.RUSSIA;
        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(country)).thenReturn("Добро пожаловать");
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationServiceImpl);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        final String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSendENText(){
        final String expected = "Welcome";
        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.USA)).thenReturn("Welcome");
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.45.183.149")).thenReturn(
                new Location("New York", Country.USA, null,  0));
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.45.183.149");
        final String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);

    }
}
