package control;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiReaderTest {

    @Test
    void WeatherApiReaderTest(){
        WeatherApiReader weatherApiReader = new WeatherApiReader();
        assertNotNull(weatherApiReader.sky);
        assertNotEquals(0, weatherApiReader.humidity);
    }

}