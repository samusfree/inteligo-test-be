package pe.inteligo.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.inteligo.test.integration.dto.LocationPoint;
import pe.inteligo.test.service.LocationService;

import java.io.IOException;
import java.util.List;

@RestController
public class LocationRest {
    @Autowired
    LocationService locationService;

    @GetMapping("/getAtms")
    public List<LocationPoint> getAtms() throws IOException {
        return locationService.listLocationPoints();
    }
}
