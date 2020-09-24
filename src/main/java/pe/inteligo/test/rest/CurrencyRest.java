package pe.inteligo.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.inteligo.test.integration.dto.CurrencyResponse;
import pe.inteligo.test.service.CurrencyService;

import java.io.IOException;

@RestController
public class CurrencyRest {
    @Autowired
    CurrencyService service;

    @GetMapping("/getExchangeRate")
    public CurrencyResponse getExchangeRate() throws IOException {
        return service.getCurrency();
    }
}
