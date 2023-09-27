package entity;

import DTO.WeatherDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class WeatherEntity
{
    @Id
    int zipCode;
    String name;

    public WeatherEntity(int zipCode, String name)
    {
        this.zipCode = zipCode;
        this.name = name;
    }

    @OneToMany(mappedBy = "weatherEntity", cascade = CascadeType.ALL)
    private Set<WeatherDTO> weatherDTOS = new HashSet<>();

    public void addWeatherDTO(WeatherDTO weatherDTO)
    {
        weatherDTOS.add(weatherDTO);
        if(weatherDTO != null)
        {
            weatherDTO.setWeatherEntity(this);
        }
    }
}

