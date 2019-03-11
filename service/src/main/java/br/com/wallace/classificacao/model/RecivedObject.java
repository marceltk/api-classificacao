package br.com.wallace.classificacao.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecivedObject<T extends Solicitacao> {

    @JsonProperty("endPoint")
    private String endPoint;

    @JsonProperty("threshold")
    private Double threshold;

    @JsonProperty("object")
    private T object;
}
