package DTO;

import entity.WeatherEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @ManyToOne
    private WeatherEntity weatherEntity;
}