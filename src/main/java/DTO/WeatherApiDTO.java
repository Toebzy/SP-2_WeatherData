package DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter

public class WeatherApiDTO
{
    private String LocationName;
    private CurrentDataDTO CurrentData;
}
