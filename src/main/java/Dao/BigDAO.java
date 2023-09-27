package Dao;

import DTO.WeatherDTO;
import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;

public class BigDAO <T>
{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherdatabase");

    public void save(T t)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
    }
}
