# Api em java e Srping security
### COMO RODAR APLICAÇÃO

`Clone o repositório`
```bash
    git clone https://github.com/fcss-dev/api-tweets-security.git
```

`Verifique pré-requisitos`
```bash
    java -version # >= xxxx
    mvn -v
    git --version
    docker --version 
    docker compose version 
```

`Configurar variáveis e arquivo de propriedades`
```text
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=admin
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
```

`Ajustar as variaveis do docker-compose.yml`
```docker
environment:
  MYSQL_ROOT_PASSWORD:123
  MYSQL_DATABASE:mydb
  MYSQL_USER:admin
  MYSQL_PASSWORD:123
```

`Levantar MySQL via Docker`
```bash
  docker compose up -d
```

`Rodar a aplicação`
```bash
  mvn spring-boot:run
```


### ROTAS  DA API
#### Todas as rotas da API devem ser chamadas a partir dessa URL.

`API Base URL:` - http://localhost:8080

---


ACTION - `Fazer Login`  
METHOD - `POST`    
ROUTE - `/login`  

`REQUEST - JSON > BODY :`  
```json
{
  "username": "admin",
  "password": "123"
}
```

- Return - 200 OK com acessToken


---

`ACTION - Registrar Usuarios`  
`METHOD - POST`    
`ROUTE - /users`  

`REQUEST - JSON > BODY :`
```json
{
  "username": "user2",
  "password": "234"
}
```
- Return - 200 OK

---


`ACTION - Listar Usuarios`   
`METHOD - GET`     
`ROUTE - /users`   

`PERMISSION - Auth - Bearer Token`
```json
{
  "Token": "acessToken"
}
```
- Return - 200 OK

---



`ACTION - Adicionar tweets`  
`METHOD - POST`    
`ROUTE - /tweets`  

`REQUEST - JSON > BODY :`
```json
{
  "content" : "The first tweet, Hello world!"
}
```

`PERMISSION - Auth > Bearer Token`  

```json
{
  "Token": "acessToken"
}
```
- Return - 200 OK

---

`ACTION - Deletar tweets`   
`METHOD - DELETE`     
`ROUTE - /tweets/{id}`  

`PERMISSION - Auth > Bearer Token`  
```json
{
  "Token": "acessToken"
}
```

- Return - 200 OK

---

`ACTION - Exibir tweets`   
`METHOD - GET`     
`ROUTE - /feed`

`PERMISSION - Auth - Bearer Token`
```json
{
  "Token": "acessToken"
}
```

- Return - 200 OK

---