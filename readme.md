
# Prova de Sufiência - Sistema de Comandas

## Utilizado
- [Intellij](https://www.jetbrains.com/pt-br/idea/)
- [Java11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [MariaDB](https://mariadb.org/)
- [Maven](https://maven.apache.org/)
- [SpringBoot 2.4.3](https://spring.io/projects/spring-boot)

#### Dependências
- Lombok
- JPA
- Swagger
- Spring security
- Hibernate


## Swagger
```bash
http://localhost:8080/swagger-ui/index.html
```

## Como utilizar o projeto em sua maquina

```bash
 git clone https://github.com/majutestoni/prova.git
```
- Abrir em alguma IDEA de sua preferência e dar reload com o Maven

- Necessário criar um database MariaDB ou Mysql como o nome "prova", demais informações de banco de dados disponível em: **application.properties**


#### Tabela Usuarios
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório** |
| `nome` | `varchar` | **Obrigatório**. |
| `telefone` | `varchar` | **Obrigatório**. tamanho =  de 11 a 14 |
| `senha` | `varchar` | **Obrigatório**.|



#### Tabela Produtos
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório** |
| `nome` | `varchar` | **Obrigatório**. |
| `preco` | `double` | **Obrigatório**|

#### Tabela Comandas
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório** |
| `usuario_id` | `Long` | **Obrigatório**. referencia id da tabela usuarios |

#### Tabela comanda_produto
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `comanda_id` | `Long` | **Obrigatório** |
| `produto_id` | `Long` | **Obrigatório** |



