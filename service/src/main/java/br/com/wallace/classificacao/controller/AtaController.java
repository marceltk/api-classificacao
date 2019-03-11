package br.com.wallace.classificacao.controller;

import br.com.wallace.classificacao.model.Ata;
import br.com.wallace.classificacao.model.ResponseClassify;
import br.com.wallace.classificacao.send.SendRequest;
import br.com.wallace.classificacao.util.ControllerUtil;
import br.com.wallace.classificacao.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AtaController {

    private final ControllerUtil controllerUtil;
    private final SendRequest sendRequest;

    @Autowired
    public AtaController(ControllerUtil controllerUtil, SendRequest sendRequest) {
        this.controllerUtil = controllerUtil;
        this.sendRequest = sendRequest;
    }


    public Mono<ResponseEntity<?>> classifyAta(final Ata ata,
                                               final String endPoint,
                                               final Double threshold) {
        final ResponseClassify responseClassify =
                (ResponseClassify) sendRequest.sendRequest(JsonUtil.writeJson(ata), endPoint, ResponseClassify.class);
        return controllerUtil.verifyResponse(responseClassify, threshold);
    }
}
