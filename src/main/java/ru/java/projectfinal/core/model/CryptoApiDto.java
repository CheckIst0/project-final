package ru.java.projectfinal.core.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoApiDto {
    @ApiModelProperty("Id записи")
    private Long id;
    @ApiModelProperty("Время")
    @JsonAlias({"time", "LASTUPDATE"})
    private String time;
    @ApiModelProperty("Название криптовалюты")
    @JsonAlias({"FROMSYMBOL"})
    private String name;
    @ApiModelProperty("Валюта")
    @JsonAlias({"TOSYMBOL"})
    private String currency;
    @ApiModelProperty("Цена криптовалюты в указанный момент времени")
    @JsonAlias({"USD", "PRICE"})
    private Double price;
    @ApiModelProperty("Цена максимума")
    @JsonAlias({"high", "HIGH24HOUR"})
    private Double high;
    @ApiModelProperty("Цена минимума")
    @JsonAlias({"low", "LOW24HOUR"})
    private Double low;
    @ApiModelProperty("Цена открытия")
    @JsonAlias({"open", "OPEN24HOUR"})
    private Double open;
    @ApiModelProperty("Цена закрытия")
    @JsonAlias({"close"})
    private Double close;
}