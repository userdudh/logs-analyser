# Log Analyser

Projeto feito em Java para ler e analisar arquivos de log do servidor Apache.

## Objetivo

O programa lê um arquivo de log e mostra informações como:

- As 5 maiores respostas em bytes  
- Requisições que não foram respondidas com sucesso  
- Porcentagem de sistemas operacionais usados em 2021  
- Média de tamanho de respostas para requisições POST em 2021

## Como usar

1. Coloque o arquivo `access.log` na pasta:


src/main/java/br/upe/logsanalyser/resources/


2. No código, verifique se o caminho do arquivo está correto.

3. Execute a classe `Main` para rodar o programa.

4. Escolha uma opção no menu exibido no console.

## Tecnologias

- Java 21  
- Maven  
- Git  
- JUnit (para testes)

