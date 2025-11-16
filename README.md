# API RESTful para Monitoramento de Saúde Mental e Bem-Estar

## 1. Contexto e Objetivo do Projeto

A saúde mental e o bem-estar são prioridades globais, e a tecnologia surge como uma aliada poderosa para o monitoramento e apoio. Este projeto consiste no desenvolvimento de uma **API RESTful** em Java com Spring Boot para uma plataforma de monitoramento de saúde mental, conforme os requisitos da Global Solution - O Futuro do Trabalho.

A solução visa permitir que pacientes registrem diariamente seu estado emocional (humor, ansiedade, sono) e que a plataforma gerencie a conexão com profissionais de saúde e recursos de apoio.

### Conexão com o Tema

O projeto se conecta diretamente com o tema **"O Futuro do Trabalho"** ao fornecer ferramentas de monitoramento de bem-estar, essenciais para prevenir o **burnout** e o estresse no ambiente profissional. Além disso, está alinhado com a **ODS 3 (Saúde e Bem-Estar)** e indiretamente com a **ODS 8 (Trabalho Decente e Crescimento Econômico)**, promovendo ambientes de trabalho mais saudáveis e produtivos.

## 2. Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
| :--- | :--- | :--- |
| **Java** | 21 (LTS) | Linguagem de programação principal. |
| **Spring Boot** | 3.2.5 | Framework para desenvolvimento rápido de APIs REST. |
| **Spring Data JPA** | 3.2.5 | Abstração para persistência de dados. |
| **H2 Database** | 2.2.224 | Banco de dados relacional em memória, ideal para desenvolvimento e testes. |
| **Lombok** | 1.18.30 | Redução de código boilerplate (getters, setters, construtores). |
| **Bean Validation** | Jakarta Validation | Validação de dados em DTOs e Entidades. |

## 3. Arquitetura e Domínio

A API segue o padrão de **Arquitetura em Camadas** (Controller → Service → Repository) e utiliza **DTOs (Data Transfer Objects)** para desacoplar a camada de apresentação da camada de persistência.

### Entidades Principais

O domínio é composto pelas seguintes entidades:

*   `Paciente` (CRUD Completo)
*   `RegistroDiario` (CRUD Completo, relacionado a `Paciente`)
*   `ProfissionalSaude`
*   `Consulta` (relacionada a `Paciente` e `ProfissionalSaude`)
*   `RecursoApoio`

## 4. Configuração e Execução

### Pré-requisitos

*   Java Development Kit (JDK) 21 ou superior.
*   Apache Maven 3.6 ou superior.

### Passo a Passo

1.  **Clone o repositório** (assumindo que o código está em um repositório):

    # Exemplo: git clone https://github.com/L-A-N-E/GS_2_Nicolas_-_Lucas_Java
    # cd mental-health-api


2.  **Configuração do Banco de Dados (H2)**:
    O projeto está configurado para usar o **H2 Database** em modo **em memória**. A configuração está no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:h2:mem:saudementaldb
    spring.jpa.hibernate.ddl-auto=create-drop
    ```
    O parâmetro `create-drop` garante que o esquema do banco seja criado automaticamente ao iniciar a aplicação e destruído ao parar. O componente `DataSeeder` (item 5) é responsável por carregar os dados iniciais (seeds).

3.  **Executar a Aplicação**:
    Utilize o Maven para compilar e executar o projeto:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    A API estará acessível em: `http://localhost:8080`.

## 5. Endpoints da API (CRUDs Completos)

Os endpoints implementados com CRUD completo são para as entidades **Paciente** e **RegistroDiario**.

### 5.1. Pacientes (`/pacientes`)

| Método | URL | Descrição | Status HTTP |
| :--- | :--- | :--- | :--- |
| `POST` | `/pacientes` | Cria um novo paciente. | `201 Created` |
| `GET` | `/pacientes` | Lista todos os pacientes. | `200 OK` |
| `GET` | `/pacientes/{id}` | Busca um paciente pelo ID. | `200 OK` / `404 Not Found` |
| `PUT` | `/pacientes/{id}` | Atualiza um paciente existente. | `200 OK` / `404 Not Found` |
| `DELETE` | `/pacientes/{id}` | Remove um paciente. | `204 No Content` / `404 Not Found` |

**Exemplo de Requisição `POST /pacientes`**

*   **URL:** `http://localhost:8080/pacientes`
*   **Body (JSON):**
    ```json
    {
      "nome": "João da Silva",
      "email": "joao.silva@exemplo.com",
      "dataNascimento": "1990-01-01",
      "telefone": "11999998888"
    }
    ```

### 5.2. Registros Diários (`/registros-diarios`)

| Método | URL | Descrição | Status HTTP |
| :--- | :--- | :--- | :--- |
| `POST` | `/registros-diarios` | Cria um novo registro diário. | `201 Created` |
| `GET` | `/registros-diarios` | Lista todos os registros diários. | `200 OK` |
| `GET` | `/registros-diarios/{id}` | Busca um registro pelo ID. | `200 OK` / `404 Not Found` |
| `PUT` | `/registros-diarios/{id}` | Atualiza um registro existente. | `200 OK` / `404 Not Found` |
| `DELETE` | `/registros-diarios/{id}` | Remove um registro. | `204 No Content` / `404 Not Found` |

**Exemplo de Requisição `POST /registros-diarios`**

*   **URL:** `http://localhost:8080/registros-diarios`
*   **Body (JSON):**
    ```json
    {
      "pacienteId": 1,
      "dataRegistro": "2025-11-14",
      "nivelHumor": 4,
      "nivelAnsiedade": 2,
      "horasSono": 7,
      "notas": "Dia produtivo e com pouca ansiedade."
    }
    ```
    *Observação: O `pacienteId` deve ser um ID de paciente existente (ex: 1, que é criado pelo DataSeeder).*

## 6. Validações e Tratamento de Erros

### Validações (Bean Validation)

As validações são aplicadas nos DTOs de requisição. Em caso de falha de validação, a API retorna **HTTP 400 (Bad Request)** com uma lista detalhada dos erros.

**Exemplos de Validações:**

*   `PacienteRequestDTO`: `@NotBlank` (nome, email, telefone), `@Email` (email), `@Past` (dataNascimento).
*   `RegistroDiarioRequestDTO`: `@NotNull` (campos), `@Min(1)` e `@Max(5)` (níveis de humor/ansiedade), `@PastOrPresent` (dataRegistro).

### Tratamento de Exceções

O tratamento de exceções é centralizado no `GlobalExceptionHandler` (`@RestControllerAdvice`).

*   **404 Not Found:** Retornado para exceções customizadas como `PacienteNaoEncontradoException` e `RegistroDiarioNaoEncontradoException`.
*   **400 Bad Request:** Retornado para erros de validação (`MethodArgumentNotValidException`).
*   **500 Internal Server Error:** Retornado para exceções não tratadas.

## 7. Seeds (Dados Iniciais)

A classe `DataSeeder` é executada no início da aplicação para popular o banco H2 com dados de exemplo, garantindo que os endpoints de listagem e busca funcionem imediatamente.

**Dados Iniciais Carregados:**

*   2 `ProfissionalSaude`
*   2 `Paciente`
*   3 `RegistroDiario`
*   2 `Consulta`
*   2 `RecursoApoio`

## 8. Como Testar

Você pode usar ferramentas como **Postman**, **Insomnia** ou o comando `curl` para interagir com a API.

### Exemplo com `curl` (Criar Paciente)

```bash
curl -X POST http://localhost:8080/pacientes \
-H "Content-Type: application/json" \
-d '{
  "nome": "Novo Paciente Teste",
  "email": "teste@api.com",
  "dataNascimento": "1998-05-20",
  "telefone": "99999999999"
}'
```

---
