package br.com.wallace.classificacao.routes;

import br.com.wallace.classificacao.controller.PublicacaoController;
import br.com.wallace.classificacao.model.Publicacao;
import br.com.wallace.classificacao.model.RecivedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PublicacaoRoutes {

    private final PublicacaoController publicacaoController;

    @Autowired
    public PublicacaoRoutes(PublicacaoController publicacaoController) {
        this.publicacaoController = publicacaoController;
    }


    @RequestMapping(value = "/api/v1/classification/publicacao", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<?>> publicacao(@Validated @RequestBody RecivedObject recivedObject) {
        return publicacaoController.classifyPublicacao(
                ((Publicacao) recivedObject.getObject()),
                recivedObject.getEndPoint(),
                recivedObject.getThreshold())
                .defaultIfEmpty(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }
}