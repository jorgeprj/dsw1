# AA3: desenvolvimento de uma API REST 

## Sistema 
Sistema para oferta de vagas de empregos 

## Roteiro de execução 

- Abra a pasta ```/Empregos``` no terminal do seu Sistema Operacional
- Execute o comando:
  
```
mvn spring-boot:run
```

- Depois é só entrar na web:

```
localhost:8081
```

## Banco de Dados
Foi utilizado o **MySQL** para a criação do banco de dados.
```
login: root
senha: root
```

```
nome da tabela: Empregos
```

Algumas coisas já estão adicionadas no banco:

### Admin

```
login: admin@email.com
senha: admin
```
### Empresa

```
login: ufscar@email.com
senha: ufscar
```

### Profissional

```
login: jorge@email.com
senha: jorge
```

### Entrevista

```
empresa: jorge@email.com
profissional: jorge
data/hora: 30/07/2023 15:00
```

## API REST

### Empresas
```
localhost:8081/empresas/
```

```
localhost:8081/empresas/{id}
```

```
localhost:8081/empresas/cidades/{nome}
```

```
localhost:8081/empresas/{id}
```

### Profissional
```
localhost:8081/profissionais/
```

```
localhost:8081/profissionais/{id}
```

### Entrevistas
```
localhost:8081/entrevistas/
```

```
localhost:8081/entrevistas/{id}
```

```
localhost:8081/entrevistas/empresas/{id}
```

```
localhost:8081/entrevistas/profissionais/{id}
```