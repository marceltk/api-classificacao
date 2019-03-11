package br.com.wallace.classificacao.security;


import br.com.wallace.classificacao.domain.enums.RoleEnum;
import br.com.wallace.classificacao.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private static final String ROLE = "role";
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String authToken = authentication.getCredentials().toString();
        final String username = Optional.of(authToken)
                .map(jwtUtil::getUsernameFromToken)
                .orElse(null);
        final List<String> rolesMap = Optional.ofNullable(authToken)
                .filter(jwtUtil::validateToken)
                .map(jwtUtil::getAllClaimsFromToken)
                .map(claims -> claims.get(ROLE, List.class))
                .orElse(Collections.emptyList());
        final List<SimpleGrantedAuthority> authorityList = rolesMap
                .stream()
                .map(RoleEnum::valueOf)
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());
        if (!authorityList.isEmpty()) {
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorityList
            ));
        } else {
            return Mono.empty();
        }
    }
}