package pe.inteligo.test.integration.api;

import pe.inteligo.test.integration.dto.CurrencyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyAPI {
    @GET("latest/")
    Call<CurrencyResponse> getCurrencyFromDolar(@Query("base") String base, @Query("symbols") String symbols);
}
