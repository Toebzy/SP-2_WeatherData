import DTO.WeatherDTO;
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
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherdatabase");
        WeatherScraper weatherScraper = new WeatherScraper();
        List<WeatherDTO> weatherDTOS = weatherScraper.scrape();
        weatherDTOS.forEach(System.out::println);
        WeatherEntity weatherEntity = new WeatherEntity(2800, "Kongens Lyngby");
        weatherDTOS.forEach(weatherEntity::addWeatherDTO);
        weatherEntity.getWeatherDTOS().forEach(System.out::println);
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(weatherEntity);
            em.getTransaction().commit();
        }
        System.exit(0);
    }
}