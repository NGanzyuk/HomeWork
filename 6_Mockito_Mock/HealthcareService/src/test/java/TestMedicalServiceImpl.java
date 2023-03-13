import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestMedicalServiceImpl {
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
    public class PatientInfoFileRepository implements PatientInfoRepository {
        @Override
        public PatientInfo getById(String id) {
            return null;
        }

        @Override
        public String add(PatientInfo patientInfo) {
            return null;
        }

        @Override
        public PatientInfo remove(String id) {
            return null;
        }

        @Override
        public PatientInfo update(PatientInfo patientInfo) {
            return null;
        }
    }

    public class SendAlertServiceImpl implements SendAlertService{
        @Override
        public void send(String message) {
            System.out.println(message);
        }
    }

    @Test
    public void testCheckBloodPressure(){
        SendAlertServiceImpl sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        PatientInfoFileRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById("1")).thenReturn(new PatientInfo("Семен", "Михайлов",
                LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))));
        final BloodPressure bloodPressure = new BloodPressure(200,180);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkBloodPressure("1", bloodPressure);
    }

    @Test
    public void testCheckTemperature(){
        SendAlertServiceImpl sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        PatientInfoFileRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById("1")).thenReturn(new PatientInfo("Семен", "Михайлов",
                LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))));
        final BigDecimal temperature = BigDecimal.valueOf(40.0);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkTemperature("1", temperature);
    }

    @Test
    public void testNoMessage() {
        SendAlertServiceImpl sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        PatientInfoFileRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById("1")).thenReturn(new PatientInfo("Семен", "Михайлов",
                LocalDate.of(1982, 1, 16),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))));
        final BigDecimal temperature = BigDecimal.valueOf(36.6);
        final BloodPressure bloodPressure = new BloodPressure(125,78);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, sendAlertService);
        medicalService.checkBloodPressure("1", bloodPressure);
        medicalService.checkTemperature("1", temperature);
    }
}
