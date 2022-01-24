package ru.java.projectfinal.core.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coin {

    @JsonAlias({"BTC", "ETH", "LINK", "LUNA", "ATOM", "BNB", "FTM", "SOL", "DOT", "ADA"})
    private Price price;

    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Price {
        @JsonProperty("USD")
        private Double price;
    }
}
