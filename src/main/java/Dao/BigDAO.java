package Dao;
import DTO.WeatherDTO;
import config.HibernateConfig;
import entity.WeatherEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class BigDAO <T>
{

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherdatabase");

    public void saveWeather(WeatherEntity weatherEntity) {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            WeatherEntity existingEntity = em.find(WeatherEntity.class, weatherEntity.getZipCode());
            if (existingEntity == null) {
                em.persist(weatherEntity);
            } else {
                System.out.println("Entity with primary key already exists. Skipping update.");
            }
            em.getTransaction().commit();
        }
    }


    public WeatherDTO getWeatherByDateAndZip(LocalDate date, int zipCode)
    {
        try(EntityManager em = emf.createEntityManager())
        {
                TypedQuery<WeatherDTO> q1 = em.createQuery("SELECT wdto FROM WeatherDTO wdto WHERE wdto.date = :date AND wdto.weatherEntity.zipCode = :zipCode", WeatherDTO.class);
            q1.setParameter("date", date);
            q1.setParameter("zipCode", zipCode);
            return q1.getSingleResult();
        }
    }

    public WeatherDTO getHighestTemp() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<WeatherDTO> q1 = em.createQuery("SELECT wd FROM WeatherDTO wd ORDER BY wd.maxTemp DESC", WeatherDTO.class);
            q1.setMaxResults(1);
            List<WeatherDTO> resultList = q1.getResultList();

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            } else {
                return null;
            }
        }
    }

    public WeatherDTO getLowestTemp() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<WeatherDTO> q1 = em.createQuery("SELECT wd FROM WeatherDTO wd ORDER BY wd.minTemp", WeatherDTO.class);
            q1.setMaxResults(1);
            List<WeatherDTO> resultList = q1.getResultList();

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            } else {
                return null;
            }
        }
    }

    public List<WeatherDTO> getAllWeather()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<WeatherDTO> q1 = em.createQuery("SELECT wd FROM WeatherDTO wd", WeatherDTO.class);
            return q1.getResultList();
        }
    }

    public T update(T t)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            T updatedT = em.merge(t);
            em.getTransaction().commit();
            return updatedT;
        }
    }
    public void delete(T t)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }
}