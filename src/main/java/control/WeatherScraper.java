package control;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Getter
@Setter
@ToString

public class WeatherScraper
{
    String url = "https://www.dmi.dk/lokation/show/DK/2618468/Kongens_Lyngby/";
    Document document;
    public WeatherScraper()
    {

    }

    public void scrape()
    {
        try
        {
            document = Jsoup.connect(url).get();
            Elements elements = document.select(".jss52");
            System.out.println(elements.text());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
