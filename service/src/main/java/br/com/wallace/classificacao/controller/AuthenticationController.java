package br.com.wallace.classificacao.controller;

import br.com.wallace.classificacao.domain.repository.UserRepository;
import br.com.wallace.classificacao.jwt.Encoder;
import br.com.wallace.classificacao.jwt.JwtUtil;
import br.com.wallace.classificacao.model.AuthRequestModel;
import br.com.wallace.classificacao.model.AuthResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final Encoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationController(JwtUtil jwtUtil, Encoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Mono<ResponseEntity<?>> auth(AuthRequestModel authRequestModel) {
        return userRepository.findFirstByUserName(authRequestModel.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(authRequestModel.getPassword()).equalsIgnoreCase(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponseModel(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
