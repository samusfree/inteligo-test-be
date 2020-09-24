package pe.inteligo.test.service;

import pe.inteligo.test.integration.dto.LocationPoint;

import java.io.IOException;
import java.util.List;

public interface LocationService {
    List<LocationPoint> listLocationPoints() throws IOException;
}
