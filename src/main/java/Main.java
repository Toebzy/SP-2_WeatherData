import DTO.WeatherDTO;
import Dao.BigDAO;
import config.HibernateConfig;
import control.WeatherApiReader;
import control.WeatherScraper;
import entity.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        WeatherScraper weatherScraper = new WeatherScraper();
        List<WeatherDTO> weatherDTOS = weatherScraper.scrape();
        weatherDTOS.forEach(System.out::println);
        WeatherEntity weatherEntity = new WeatherEntity(2900, "Kongens Lyngby");
        weatherDTOS.forEach(weatherEntity::addWeatherDTO);
        weatherEntity.getWeatherDTOS().forEach(System.out::println);
        BigDAO<WeatherEntity> weatherEntityBigDAO = new BigDAO<>();
        weatherEntity.setName("Lyngby Kongens");
        weatherEntityBigDAO.saveWeather(weatherEntity);
        weatherEntityBigDAO.update(weatherEntity);
        System.out.println(weatherEntityBigDAO.getHighestTemp());
        System.out.println(weatherEntityBigDAO.getLowestTemp());
        System.out.println(weatherEntityBigDAO.getAllWeather());

        System.exit(0);
    }
}