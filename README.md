# Api em java e Spring security
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


# 📘 API Documentation

**Base URL:** `http://localhost:8081`

------------------------------------------------------------------------

## 🔐 **Auth**

### **POST /login --- Login**

**Body**

``` json
{
  "username": "admin",
  "password": "123"
}
```

**Response:** `200 OK` (accessToken)

------------------------------------------------------------------------

## 👤 **Users**

### **POST /users --- Registrar usuário**

**Body**

``` json
{
  "username": "user2",
  "password": "234"
}
```

**Response:** `200 OK`

------------------------------------------------------------------------

### **GET /users --- Listar usuários**

**Auth:** Bearer Token\
**Response:** `200 OK`

------------------------------------------------------------------------

## 💬 **Tweets**

### **POST /tweets --- Criar tweet**

**Auth:** Bearer Token\
**Body**

``` json
{
  "content": "The first tweet, Hello world!"
}
```

**Response:** `200 OK`

------------------------------------------------------------------------

### **DELETE /tweets/{id} --- Deletar tweet**

**Auth:** Bearer Token\
**Response:** `200 OK`

------------------------------------------------------------------------

### **GET /feed --- Listar tweets**

**Auth:** Bearer Token\
**Response:** `200 OK`
