package sender;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationServiceImpl implements LocalizationService {
    @Override
    public String locale(Country country) {
        return "Welcome";
    }
}
