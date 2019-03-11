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
public class Publicacao extends Solicitacao {
    @JsonProperty("TextoPublicacao")
    private String textoPublicacao;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("sistema")
    private String sistema;
}
