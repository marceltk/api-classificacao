# Descricao
## Melhorias futuras
 - Implantar filas de mensagens com RabbitMq.
 - JavaDoc do código.
 - Colocar projetos no docker.
 - Melhorias dos testes unitarios.
 - Melhoria do front.
 
## Linguagem e framework utilizada
   - A linguagem utilizada foi o JAVA.
   - O framework utilizado foi o Spring, em sua versão 5, com spring boot 2.0, que é utilizada para criar APIs reativas de processamento assíncrono.
   - Também foi utilizada a Flunt Api do Apache para envio de requisições aos servições em python de classificação.
   - Loombok foi utilizado como metaprogramação e abstração dos dados do model.
   - Jackson para manipulação e configuração de JSON.
    
## Trafego de arquivos intercambiaveis
 - Foi escolhido JSON como arquivo intercambiavel para a API por sua praticidade, facil implantação e leve. 
 

## Design Pattern
 Foi utilizado os design patterns Builder (Principalmente para os models), Adapter, para a configuração da autenticação com JWT, Observer (Natualmente implementado pelo React do Spring 5 para controle de requesições assíncronas) e Mediator, como Middleware das requisições para validação da autenticação.

## Controle de log
  Foi implementado um controle de log de todas as requisições recebidas pela api, onde são salvos o método utilizado, o link, a hora da requisição, o ip do solicitante, entre outros dados.
  
## Persistencia dos dados
 A persistencia dos dados, como log e usuarios para autenticação, estão todas em um banco de dados NonSQL MongoDB.
 
 A escolha pelo Mongo foi pelo fato dele apresentar suporte reativo, melhorando assim o desempenho da api em suas requisições e solicitações ao bando de dados, se, gargalos. E também por se tratar de um banco da dados rapido e leve.
 
## Autenticacao
 A autenticação foi feita utilizando JWT, que é muito utilizado, seguro e de baixa complexidade. Não é OAuth, porém possui um nivel auto de segurança e confiabilidade.
 
 O token gerado é enviado via header, com o header Authentication, que é validado pelo middleware do serviço.
 
## Client Side
 Desenvolvido utilizando Angular em sua versão 2 e Bootstrap.
 
## Instruções de execução
 - Criar uma instância do mongoDB.
 - Criar um usuario com o username: admin e password: amin.
 - Executar o aplicativo Java do backend.
 - Abrir o index do front.
 - Realizar o login com as credencias.
 - Configurar os endPoints.
 - Preencher os campos e enviar as requisições.
 
 
 