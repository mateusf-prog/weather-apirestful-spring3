package br.com.mateus.apiweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import br.com.mateus.apiweather.models.city.CityResponse;
import br.com.mateus.apiweather.models.dto.CityData;
import br.com.mateus.apiweather.models.dto.WeatherData;
import br.com.mateus.apiweather.services.WeatherService;


@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;
    @Mock
    private WeatherData weatherData;
    @Mock
    private CityData cityData;
    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testValidateLatiduteLongitude() {
        WeatherService weatherService = new WeatherService();
        Assertions.assertTrue(weatherService.validateLatiduteLongitude(-23.18, -45.88));
        Assertions.assertFalse(weatherService.validateLatiduteLongitude(-50, 190));
    }

    @Test
    public void testResponseApiAdressSucess() {

        // TEST SUCESS 
        double lat = -23.18;    // valid    
        double lon = -45.88;    // valid

        // url valid (public key)
        String url = "https://www.mapquestapi.com/geocoding/v1/reverse?key=lRcKZSTRNKOtLlmx8gmL3W3FpGC5twxJ&location=-23.18,-45.88";

        // expected response
        CityResponse expectedCityResponse = new CityResponse();
        when(restTemplate.getForObject(url, CityResponse.class)).thenReturn(expectedCityResponse);

        CityResponse actualCityResponse = weatherService.responseApiAdress(lat, lon);
        assertEquals(expectedCityResponse, actualCityResponse);
    }
}
