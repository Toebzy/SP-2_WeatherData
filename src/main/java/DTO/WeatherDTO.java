package DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class WeatherDTO
{
    LocalDate date;
    double maxTemp;
    double minTemp;
    String wind;
    String rain;
}
