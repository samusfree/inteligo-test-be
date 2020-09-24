package pe.inteligo.test.service;

import pe.inteligo.test.integration.dto.CurrencyResponse;

import java.io.IOException;

public interface CurrencyService {
    CurrencyResponse getCurrency() throws IOException;
}
