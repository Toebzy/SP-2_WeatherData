package control;

import DTO.WeatherDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WeatherScraper
{
    String url = "https://www.yr.no/nb/v%C3%A6rvarsel/daglig-tabell/2-2618468/Denmark/Capital%20Region/Lyngby-T%C3%A5rb%C3%A6k/Kgs.%20Lyngby";
    Document document;

    public List<WeatherDTO> scrape()
    {
        List<WeatherDTO> weatherDTOS = new ArrayList<>();
        try
        {
            document = Jsoup.connect(url).get();
            Elements elements = document.select(".daily-weather-list-item");
            elements.forEach(el -> {

                String date, maxTemp, minTemp, wind, rain;
                date = el.select("h3 time").attr("datetime");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date, formatter);
                maxTemp = el.select(".min-max-temperature__max").text();
                minTemp = el.select(".min-max-temperature__min").text();
                rain = el.select(".Precipitation-module__main-sU6qN").text();
                wind = el.select(".daily-weather-list-item__wind").text();
                WeatherDTO weatherDTO = WeatherDTO.builder()
                        .date(localDate)
                        .maxTemp(maxTemp)
                        .minTemp(minTemp)
                        .wind(wind)
                        .rain(rain)
                        .build();
                weatherDTOS.add(weatherDTO);
            });
            return weatherDTOS;
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}