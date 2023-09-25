import DTO.WeatherDTO;
import control.WeatherScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
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