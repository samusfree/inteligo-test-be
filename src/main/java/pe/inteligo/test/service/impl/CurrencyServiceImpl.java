package pe.inteligo.test.service.impl;

import org.springframework.stereotype.Service;
import pe.inteligo.test.dto.ExchangeRateDTO;
import pe.inteligo.test.integration.api.CurrencyAPI;
import pe.inteligo.test.integration.constants.APIConfiguration;
import pe.inteligo.test.integration.dto.CurrencyResponse;
import pe.inteligo.test.service.CurrencyService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class CurrencyServiceImpl implements CurrencyService, APIConfiguration {
    private final CurrencyAPI currencyAPI;

    public CurrencyServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CURRENCY_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        currencyAPI = retrofit.create(CurrencyAPI.class);
    }

    @Override
    public ExchangeRateDTO getCurrency() throws IOException {
        Call<CurrencyResponse> retrofitCall = currencyAPI.getCurrencyFromDolar("USD", "EUR,GBP");

        Response<CurrencyResponse> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        CurrencyResponse responseObject = response.body();
        ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
        exchangeRateDTO.setEuroExchangeRate(responseObject.getRates().get("EUR").toString());
        exchangeRateDTO.setPoundExchangeRate(responseObject.getRates().get("GBP").toString());
        return exchangeRateDTO;
    }
}
