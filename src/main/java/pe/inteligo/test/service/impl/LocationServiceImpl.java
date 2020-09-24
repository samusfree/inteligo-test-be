package pe.inteligo.test.service.impl;

import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
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
import java.util.List;

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
    public List<LocationPoint> listLocationPoints() throws IOException {
        Call<List<LocationPoint>> retrofitCall = locationAPI.listAtms();

        Response<List<LocationPoint>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
}
