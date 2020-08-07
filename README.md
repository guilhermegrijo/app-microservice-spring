# App Microservice Spring


Essa Aplicação é um exemplo de microserviços Spring que se comunicam pelo Apache Kafka porém são independentes.

- Quando um produto é criado, o microserviço de estoque emite a mensagem com o produto para o microserviço de venda e o produto é replicado lá.

- Quando é efetuado uma vendo no microservice de venda, ele emite a mensagem da venda e o microserviço de estoque cria a ordem de entrega



|Tecnologias|
| ------ |
|Spring Boot|
|Spring Kafka|
|Kafka|
|Zookeeper|
|MongoDB|
|Docker Compose|
