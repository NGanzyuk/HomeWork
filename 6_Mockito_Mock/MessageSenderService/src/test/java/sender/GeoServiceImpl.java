package sender;

import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceImpl implements GeoService {
    @Override
    public Location byIp(String ip) {
        return new Location(null, null, null, 0);
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }
}
