import control.WeatherScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        String url = "https://www.dmi.dk/lokation/show/DK/2618468/Kongens_Lyngby/";
        Document document;

        try
        {
            document = Jsoup.connect(url).get();
            System.out.println(document.select(""));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}