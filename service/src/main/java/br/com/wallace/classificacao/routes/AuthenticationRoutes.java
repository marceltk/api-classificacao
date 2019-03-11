package br.com.wallace.classificacao.routes;

import br.com.wallace.classificacao.controller.AuthenticationController;
import br.com.wallace.classificacao.model.AuthRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationRoutes {
    private final AuthenticationController authenticationController;

    @Autowired
    public AuthenticationRoutes(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }
    
    @RequestMapping(value = "/api/v1/classification/auth", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> auth(@Validated @RequestBody AuthRequestModel authRequestModel) {
        return authenticationController.auth(authRequestModel);
    }
}