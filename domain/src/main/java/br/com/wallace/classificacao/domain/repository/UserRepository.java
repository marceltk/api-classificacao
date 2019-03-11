package br.com.wallace.classificacao.domain.repository;

import br.com.wallace.classificacao.domain.model.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {
    Mono<UserModel> findFirstByUserName(String username);
}
