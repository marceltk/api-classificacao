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
public class Ata extends Solicitacao {
    @JsonProperty("Arquivos")
    private List<ArquivoModel> arquivos;
}
