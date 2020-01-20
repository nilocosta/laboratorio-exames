# laboratorio-exames
Teste de Back-End DASA

#Arquitetura
O projeto em Maven faz utilização do Spring Boot para inicialização dos recursos;
O maven resolverá todas as dependencias necessárias.

#Banco de dados
O banco H2 esta embutido no projeto e configurado para rodar em memória. Uma vez que o projeto iniciar, o banco inicializará automaticamente.
Você poderá acessar a base pelo navegador de internet no endereço http://localhost:8080/h2
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:
#ATENÇÃO: 
Os Dados serão perdidos caso o projeto pare de ser executado.

#Como Iniciar
Inicie como uma aplicação Java pela classe LaboratorioExamesApplication.java

#Collection Postman
Existe uma collection pronta para ser importada no postman com o exemplo de execução de todos os endpoints disponíveis. Ela pode ser acessado através do diretório laboratorio-exames/examples

#Documentação
A os endpoints estão documentados através do swagger pelo endereço http://localhost:8080/swagger-ui.html#/
