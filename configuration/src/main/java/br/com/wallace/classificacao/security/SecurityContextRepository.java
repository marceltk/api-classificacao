package br.com.wallace.classificacao.security;

import br.com.wallace.classificacao.domain.model.LogModel;
import br.com.wallace.classificacao.domain.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
    private static final String HASH = "HASH ";
    private static final String NOT_SUPPORTED = "NOT SUPPORTED YET.";
    private static final String DEFAULT_LOG = "{}";
    private final AuthenticationManager authenticationManager;
    private final LogRepository logRepository;

    @Autowired
    public SecurityContextRepository(AuthenticationManager authenticationManager, LogRepository logRepository) {
        this.authenticationManager = authenticationManager;
        this.logRepository = logRepository;
    }


    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        ServerHttpRequest request = swe.getRequest();
        this.saveLog(request);
        final String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        final Predicate<String> predicate = str -> str.startsWith(HASH);
        return Optional.ofNullable(header)
                .filter(predicate)
                .map(str -> str.substring(5))
                .map(str -> new UsernamePasswordAuthenticationToken(str, str))
                .map(authenticationManager::authenticate)
                .orElseGet(Mono::empty)
                .map(SecurityContextImpl::new);
    }

    private void saveLog(ServerHttpRequest request) {
        try {
            final LogModel logModel = LogModel.builder()
                    .date(new Date())
                    .endPoint(request.getPath().toString())
                    .ip(request.getRemoteAddress().toString())
                    .method(request.getMethod().toString())
                    .build();
            logRepository.insert(logModel).then().block();
        } catch (Exception e) {
            log.debug(DEFAULT_LOG, e);
        }
    }

}