package br.com.mateus.apiweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import br.com.mateus.apiweather.exception.ApiException;
import br.com.mateus.apiweather.exception.LatitudeLongitudeException;
import br.com.mateus.apiweather.models.city.CityResponse;
import br.com.mateus.apiweather.models.dto.CityData;
import br.com.mateus.apiweather.models.dto.WeatherData;
import br.com.mateus.apiweather.services.WeatherService;


@SpringBootTest
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;
    
    // mocks to be injected into weatherService
    @Mock
    private WeatherData weatherData;
    @Mock
    private CityData cityData;
    @Mock
    private RestTemplate restTemplate;

    @Test
    @DisplayName("test range valid latitude/longitude")
    public void testValidateLatiduteLongitude() {
        WeatherService weatherService = new WeatherService();
        Assertions.assertTrue(weatherService.validateLatiduteLongitude(-23.18, -45.88));
        Assertions.assertFalse(weatherService.validateLatiduteLongitude(-50, 190));
    }

    @Test
    @DisplayName("test success method")
    public void testResponseApiAdress_Success() {
        double lat = -23.18;    // valid    
        double lon = -45.88;    // valid
        String url = "https://www.mapquestapi.com/geocoding/v1/reverse?key=lRcKZSTRNKOtLlmx8gmL3W3FpGC5twxJ&location=-23.18,-45.88";

        // expected response
        CityResponse expectedCityResponse = new CityResponse();
        when(restTemplate.getForObject(url, CityResponse.class)).thenReturn(expectedCityResponse);

        CityResponse actualCityResponse = weatherService.responseApiAdress(lat, lon);
        assertEquals(expectedCityResponse, actualCityResponse);
    }

    @Test
    @DisplayName("Test values range of Latitude/Longitude")
    public void testResponseApiAdress_LatitudeLongitudeException() {
        double lat = -91;       // invalid    
        double lon = -45.88;    // valid

        assertThrows(LatitudeLongitudeException.class, () -> {
            weatherService.responseApiAdress(lat, lon);
        });
    }

    @Test
    @DisplayName("test if trhow any exception of API MapQuest")
    public void testResponseApiAdress_ApiException() {
        double lat = -23.18;    // valid    
        double lon = -45.88;    // valid
        String url = "https://www.mapquestapi.com/geocoding/v1/reverse?key=lRcKZSTRNKOtLlmx8gmL3W3FpGC5twxJ&location=-23.18,-45.88"; 

        when(restTemplate.getForObject(url, CityResponse.class)).thenThrow(ApiException.class);

        assertThrows(ApiException.class, () -> {
            weatherService.responseApiAdress(lat, lon);
        });
    }
}
