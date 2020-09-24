package pe.inteligo.test.service;

import pe.inteligo.test.dto.ExchangeRateDTO;

import java.io.IOException;

public interface CurrencyService {
    ExchangeRateDTO getCurrency() throws IOException;
}
