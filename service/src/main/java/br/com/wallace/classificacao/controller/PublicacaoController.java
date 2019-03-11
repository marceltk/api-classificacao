package br.com.wallace.classificacao.controller;

import br.com.wallace.classificacao.model.Publicacao;
import br.com.wallace.classificacao.model.ResponseClassify;
import br.com.wallace.classificacao.send.SendRequest;
import br.com.wallace.classificacao.util.ControllerUtil;
import br.com.wallace.classificacao.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Component
public class PublicacaoController {

    private final ControllerUtil controllerUtil;
    private final SendRequest sendRequest;

    @Autowired
    public PublicacaoController(ControllerUtil controllerUtil, SendRequest sendRequest) {
        this.controllerUtil = controllerUtil;
        this.sendRequest = sendRequest;
    }

    public Mono<ResponseEntity<?>> classifyPublicacao(final Publicacao publicacao,
                                                      final String endPoint,
                                                      final Double threshold) {

        publicacao.setUf(publicacao.getUf().toUpperCase(Locale.ENGLISH));
        publicacao.setSistema(publicacao.getSistema().toLowerCase(Locale.ENGLISH));
        final ResponseClassify responseClassify =
                (ResponseClassify) sendRequest.sendRequest(JsonUtil.writeJson(publicacao), endPoint, ResponseClassify.class);
        return controllerUtil.verifyResponse(responseClassify, threshold);
    }
}
