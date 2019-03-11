package br.com.wallace.classificacao;
//
//import br.com.wallace.classificacao.jwt.JWTHeadersExchangeMatcher;
//import br.com.wallace.classificacao.jwt.JWTReactiveAuthenticationManager;
//import br.com.wallace.classificacao.security.TokenAuthenticationConverter;
//import br.com.wallace.classificacao.security.TokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
//import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
//
//
//@Configuration
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final ReactiveUserDetailsServiceImpl reactiveUserDetailsService;
//    private final TokenProvider tokenProvider;
//
//    @Autowired
//    public SecurityConfiguration(ReactiveUserDetailsServiceImpl reactiveUserDetailsService, TokenProvider tokenProvider) {
//        this.reactiveUserDetailsService = reactiveUserDetailsService;
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .logout().disable()
//                .exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/v1/customers/auth").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/customers").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/customers/{id}").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/v1/customers").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/cousines/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/stores/**").permitAll()
//                .anyRequest().authenticated()
//                .authenticationManager(authenticationManager)
//                .securityContextRepository(securityContextRepository)
//                .authorizeExchange()
//                .anyExchange().authenticated();
//    }
//
//
//    @Bean
//    private AuthenticationWebFilter webFilter() {
//        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(repositoryReactiveAuthenticationManager());
//        authenticationWebFilter.setAuthenticationConverter(new TokenAuthenticationConverter(tokenProvider));
//        authenticationWebFilter.setRequiresAuthenticationMatcher(new JWTHeadersExchangeMatcher());
//        authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
//        return authenticationWebFilter;
//    }
//
//    @Bean
//    private JWTReactiveAuthenticationManager repositoryReactiveAuthenticationManager() {
//        JWTReactiveAuthenticationManager repositoryReactiveAuthenticationManager = new JWTReactiveAuthenticationManager(reactiveUserDetailsService, passwordEncoder());
//        return repositoryReactiveAuthenticationManager;
//    }
//
//    @Bean
//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

import br.com.wallace.classificacao.security.AuthenticationManager;
import br.com.wallace.classificacao.security.SecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public SecurityConfiguration(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/api/v1/classification/auth").permitAll()
                .anyExchange().authenticated()
                .and()
                .build();

    }
}