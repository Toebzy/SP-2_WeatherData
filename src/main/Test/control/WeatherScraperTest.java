package control;

import DTO.WeatherDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherScraperTest {

    WeatherScraper weatherScraper = new WeatherScraper();

    @Test
    void scrape() {
        List<WeatherDTO> data = weatherScraper.scrape();
        assertNotNull(data);
        assertNotNull(data.get(0).getDate());
        assertNotNull(data.get(0).getSky());
        assertNotNull(data.get(0).getRain());
        assertNotNull(data.get(0).getWind());
    }

}