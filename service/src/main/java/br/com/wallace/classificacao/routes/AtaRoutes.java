package br.com.wallace.classificacao.routes;

import br.com.wallace.classificacao.controller.AtaController;
import br.com.wallace.classificacao.model.Ata;
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
public class AtaRoutes {

    private final AtaController ataController;

    @Autowired
    public AtaRoutes(AtaController ataController) {
        this.ataController = ataController;
    }

    @RequestMapping(value = "/api/v1/classification/ata", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<?>> ata(@Validated @RequestBody RecivedObject recivedObject) {
        return ataController.classifyAta(
                ((Ata) recivedObject.getObject()),
                recivedObject.getEndPoint(),
                recivedObject.getThreshold())
                .defaultIfEmpty(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }
}