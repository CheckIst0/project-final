package ru.java.projectfinal.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto {

    @JsonProperty("Data")
    private DataList dataList;

    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class DataList{
        @JsonProperty("Data")
        private List<CryptoApiDto> data;
    }
}