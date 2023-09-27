package DTO;
import java.io.Serializable;
import entity.WeatherEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Entity
@IdClass(WeatherDTO.WeatherDTOId.class)
public class WeatherDTO
{
    @Id
    LocalDate date;
    double maxTemp;
    double minTemp;
    String wind;
    String rain;
    String sky;
    int humidity;
    @ManyToOne @Id
    private WeatherEntity weatherEntity;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class WeatherDTOId implements Serializable
    {
        private LocalDate date;
        private int weatherEntity;
    }
}