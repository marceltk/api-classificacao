package br.com.wallace.classificacao.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClassify {
    @JsonProperty("idSolicitacao")
    private String idSolicitacao;

    @JsonProperty("Retorno")
    private boolean retorno;

    @JsonProperty("NumeroProcesso")
    private List<NumeroProcesso> numeroProcesso;

    @JsonProperty("Resultado")
    private String resultado;

    @JsonProperty("Mensagem")
    private String mensagem;

    @JsonProperty("Confianca")
    private Double confianca;

}
