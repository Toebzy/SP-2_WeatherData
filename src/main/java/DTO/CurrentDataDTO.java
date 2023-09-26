package DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class CurrentDataDTO {
    private String temperature;
    private String skyText;
    private String humidity;
    private String windText;
}
