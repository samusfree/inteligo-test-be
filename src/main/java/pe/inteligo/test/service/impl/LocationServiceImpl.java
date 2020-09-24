package pe.inteligo.test.service.impl;

import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.inteligo.test.dto.LocationDTO;
import pe.inteligo.test.integration.api.LocationAPI;
import pe.inteligo.test.integration.constants.APIConfiguration;
import pe.inteligo.test.integration.dto.LocationPoint;
import pe.inteligo.test.integration.utils.GsonCleanConverter;
import pe.inteligo.test.service.LocationService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService, APIConfiguration {
    private final LocationAPI locationAPI;

    public LocationServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOCATION_API_BASE_URL)
                .addConverterFactory(GsonCleanConverter.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .build();
        locationAPI = retrofit.create(LocationAPI.class);
    }

    @Override
    public List<LocationDTO> listLocationPoints(String search) throws IOException {
        return getLocations(50, search);
    }

    private List<LocationDTO> getLocations(Integer size, String search) throws IOException {
        Call<List<LocationPoint>> retrofitCall = locationAPI.listAtms();
        Response<List<LocationPoint>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        List<LocationPoint> responseObject = response.body();
        if (!StringUtils.isEmpty(search)) {
            responseObject = responseObject.stream().filter(p ->
                    p.getAddress().getStreet().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        int i = 0;
        List<LocationDTO> list = new ArrayList<>();
        for (LocationPoint location : responseObject) {
            i++;
            LocationDTO dto = new LocationDTO();
            dto.setLat(location.getAddress().getGeoLocation().getLat());
            dto.setLng(location.getAddress().getGeoLocation().getLng());
            dto.setName(location.getAddress().getStreet() + " " + location.getAddress().getHousenumber() + ", " +
                    location.getAddress().getCity() + ", CP: " +
                    location.getAddress().getPostalcode());
            list.add(dto);
            if (i > size) break;
        }
        return list;
    }
}
