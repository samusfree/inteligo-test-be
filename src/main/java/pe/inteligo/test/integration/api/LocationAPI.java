package pe.inteligo.test.integration.api;

import pe.inteligo.test.integration.dto.LocationPoint;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface LocationAPI {
    @GET("locator/atms/")
    Call<List<LocationPoint>> listAtms();
}
