package br.com.wallace.classificacao.util;

import br.com.wallace.classificacao.model.ClassifyResponseModel;
import br.com.wallace.classificacao.model.ResponseClassify;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ControllerUtil {
    private static final String DEFAULT_MESSAGE = "%s - %s Confiança da IA. ";
    private static final String LINE = "\n";
    private static final String MENSSAGE_THRESHOLD = "Cofiança baixa, seria interessante enviar para uma esteira manual.";
    private static final String RESPONSE_OK = "200 OK!";
    private static final String CLASSIFY_ERROR = "ERRO DURANTE A CLASSIFICAÇÃO, FAVOR TENTAR NOVAMENTE MAIS TARDE.";

    public Mono<ResponseEntity<?>> verifyResponse(final ResponseClassify responseClassify, final Double threshold) {
        StringBuilder stringBuilder = new StringBuilder(String.format(DEFAULT_MESSAGE,
                responseClassify.getResultado(),
                String.valueOf(responseClassify.getConfianca())));
        String message;
        if (responseClassify.getMensagem().equalsIgnoreCase(RESPONSE_OK)) {
            if (responseClassify.getConfianca() < threshold) {
                stringBuilder
                        .append(LINE)
                        .append(MENSSAGE_THRESHOLD);
            }
            message = stringBuilder.toString();
        } else {
            message = CLASSIFY_ERROR;
        }
        return Mono.just(ResponseEntity.ok(ClassifyResponseModel.builder().message(message).build()));
    }
}
