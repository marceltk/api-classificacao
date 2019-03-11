package br.com.wallace.classificacao.send;

import br.com.wallace.classificacao.util.JsonUtil;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendRequest {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public Object sendRequest(final String message, final String endPoint, Object object) {
        try {
            final String response = Request.Post(endPoint)
                    .useExpectContinue()
                    .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                    .bodyString(message, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();
            return JsonUtil.readJson(response, object.getClass());
        } catch (IOException e) {
            return null;
        }
    }
}
