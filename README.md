
# API RESTful de Clima

Esta é uma API desenvolvida em Spring Boot que fornece informações climáticas com base em coordenadas de latitude e longitude, bem como detalhes sobre a localização, como nome da cidade, estado e país. 

## API's externas Utilizadas
- openweathermap.org (dados do clima por latitude e longitude)
- developer.mapquest.com (endereço por latitude e longitude)

Ambas api's utilizam uma chave criada para fins de teste e não geram custo.

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para desenvolvimento de aplicações web.
- **Spring Web**: Módulo do Spring para criar aplicativos web RESTful.
- **Lombok**: Biblioteca para reduzir a verbosidade do código Java através da geração automática de código repetitivo.
- **Maven**

## Funcionalidades

- Obter dados climáticos com base em coordenadas de latitude e longitude.

## Como Usar

1. Certifique-se de ter o Java JDK (versão 8 ou superior) instalado em sua máquina.
2. Clone este repositório para sua máquina local.
3. Abra o projeto em sua IDE de preferência (por exemplo, Eclipse, IntelliJ, VSCode, etc). 
4. Configure as chaves de API necessárias para acessar as APIs externas de clima e localização (caso aplicável).
5. Execute a aplicação Spring Boot.
6. Acesse a API usando um cliente HTTP (por exemplo, Postman) ou integrando-a em sua própria aplicação.


## Endpoints

Exemplo de respostas (JSON):
```json

Endpoint: '/'
Método: GET

# return 
{
   Home page!
}

Endpoint: /{latitude},{longitude}
ex: /35.4743,-97.5279
Método: GET

{
    Temperatura: 32.6 ºC
    Umidade: 64%
    Vento: 5.66km/h
    NUVENS DISPERSAS
    Oklahoma City/OK
    US
}
```






## Authors

- Mateus Fonseca [https://github.com/mateusf-prog]


## Testes

Não foi implementado testes nesta api, apenas testes locais simples.

