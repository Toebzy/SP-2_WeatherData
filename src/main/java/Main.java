import DTO.WeatherDTO;
import control.WeatherScraper;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        WeatherScraper weatherScraper = new WeatherScraper();
        List<WeatherDTO> weatherDTOS = weatherScraper.scrape();
        weatherDTOS.forEach(System.out::println);
    }
}