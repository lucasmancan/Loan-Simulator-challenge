# Api de simulação de emprestimos

Aplicação para simulação de empréstimos proposta como desafio de programação.

## Documentação

O sistema proposto possuí a finalidade de simular emprestimos segundo o modelo de amortização PRICE, apurando os devidos
encargos dessa operação.

### Tecnologias

* Java 11
* Micronaut 2.4.2
* JUnit

#### Banco de dados

* H2

#### API Docs

A documentação oficial em formato OpenAPI 3.0 pode ser consultada através da implementação do Swagger, disponível
em: https://simulador-consignado.herokuapp.com/swagger-ui/.

## Instruções para Devs :)

```bash
#Download do projeto
git clone https://github.com/lucasmancan/simulador-emprestimo.git

cd /simulador-emprestimo

#Instalação das dependências
mvn clean install -DskipTests

#Rodando os testes
npm run test

#Iniciando um container para a aplicação

docker build . --tag simulador-emprestimo

docker run -p 8080:8080 --name simulador-emprestimo_v1 simulador-emprestimo

```

## Licença

[MIT](https://choosealicense.com/licenses/mit/)

## Autor

[Lucas Frederico Mançan](https://www.linkedin.com/in/lucasmancan/)