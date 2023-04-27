# Projeto Sistema Bancário
## Projeto que simula um sistema de uma agência bancária gerenciando seus clientes e contas;

Para este projeto estou usando o Maven com as seguintes dependências e versões:
- Spring Boot versão 2.7.11
- Java versão 11
- Spring Boot Web
- Spring Data JPA
- Banco de Dados em memória H2
- Swagger OpenApi versão 1.6.4
- Lombok

## 1ª etapa
- Abstrai as classes do modelo do domínio e criei seus relacionamentos iniciais:
  - Criei uma classe abstrata Conta que é especializada através das classes ContaCorrente e ContaPoupanca;
  - Criei a classe Titular;
## 2ª etapa
  - Incluí as anotações do JPA referentes aos mapeamentos e relacionamentos;
  - A estratégia de herança escolhida entre a classe Conta e suas subclasses foi a SINGLE_TABLE, 
  onde é criada uma única tabela e é criado um identificador para cada classe;
  - A classe Titular tem um relacionamento @OneToMany com a classe Conta;
## 3ª etapa
  - Após os mapeamentos e relacionamentos criei o repository e um controller inicial 
  para cada classe com um Endpoint que retorna o que foi cadastrado;
  - Por fim fiz o seed do banco H2;
