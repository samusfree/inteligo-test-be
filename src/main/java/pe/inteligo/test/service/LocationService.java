package pe.inteligo.test.service;

import pe.inteligo.test.dto.LocationDTO;

import java.io.IOException;
import java.util.List;

public interface LocationService {

    List<LocationDTO> listLocationPoints(String search) throws IOException;
}
