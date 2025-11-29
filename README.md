# Api em java e Srping security
### COMO RODAR APLICAÇÃO




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