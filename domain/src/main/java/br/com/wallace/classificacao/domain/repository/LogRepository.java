package br.com.wallace.classificacao.domain.repository;

import br.com.wallace.classificacao.domain.model.LogModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LogRepository extends ReactiveMongoRepository<LogModel, String> {
}
