package Dao;

import DTO.WeatherDTO;
import config.HibernateConfig;
import control.WeatherScraper;
import entity.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BigDAOTest {
    static WeatherScraper weatherScraper;
    static List<WeatherDTO> weatherDTOS;
    static WeatherEntity weatherEntity;
    static BigDAO<WeatherEntity> weatherEntityBigDAO;
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherdatabase");
    @BeforeAll
    static void startUp(){
        weatherScraper = new WeatherScraper();
        weatherDTOS = weatherScraper.scrape();
        weatherEntity = new WeatherEntity(2900, "Kongens Lyngby Test");
        weatherDTOS.forEach(weatherEntity::addWeatherDTO);
        weatherEntityBigDAO = new BigDAO<>();
    }

    @Test
    void saveWeather() {
        weatherEntityBigDAO.saveWeather(weatherEntity);
        try(EntityManager em = emf.createEntityManager())
        {
            assertNotNull(em.find(WeatherEntity.class, weatherEntity.getZipCode()));
        }
    }

    @Test
    void getWeatherByDateAndZip() {
        LocalDate date = LocalDate.of(2023, 9, 27);
        assertNotNull(weatherEntityBigDAO.getWeatherByDateAndZip(date, 2800));
    }

    @Test
    void getTemps() {
        assertNotNull(weatherEntityBigDAO.getHighestTemp());
        assertNotNull(weatherEntityBigDAO.getLowestTemp());
    }

    @Test
    void getAllWeather()
    {
        assertNotNull(weatherEntityBigDAO.getAllWeather());
    }

    @Test
    void update() {
        weatherEntity.setName("ChangeTest");
        weatherEntityBigDAO.update(weatherEntity);
        try (EntityManager em = emf.createEntityManager()) {
            WeatherEntity weatherFound =  em.find(WeatherEntity.class, weatherEntity.getZipCode());
            assertEquals(weatherFound.getName(), "ChangeTest");
        }
    }
}