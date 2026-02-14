📘 SmartExpense — Documentação Oficial v1.0
1️⃣ Visão Geral

O SmartExpense é um sistema de auditoria automatizada de despesas corporativas.

Ele recebe dados de comprovantes fiscais, aplica regras de negócio para validação e auditoria, e classifica a despesa com base em critérios corporativos.

Versão 1.0 implementa o core transacional em arquitetura monolítica utilizando Spring Boot.

2️⃣ Problema que o Sistema Resolve

Empresas frequentemente enfrentam:

Fraudes em reembolso

Notas duplicadas

Gastos acima do limite permitido por cargo

Fornecedores bloqueados sendo utilizados

O SmartExpense automatiza essa auditoria, reduzindo risco financeiro e retrabalho manual.

3️⃣ Escopo da Versão 1.0
✔️ Incluído:

Cadastro de despesas

Auditoria automática

Persistência em banco relacional

Classificação automática da despesa

Exclusão lógica

Consulta de despesas

❌ Não incluído nesta versão:

Microserviços

OCR

Integração com Python

Autenticação JWT

Docker

4️⃣ Arquitetura

Arquitetura em camadas:

Controller → Service → Repository → Database

Separação clara de responsabilidades:

Controller: Entrada HTTP

Service: Regras de negócio

Repository: Persistência

Domain: Modelagem do problema

5️⃣ Modelagem de Domínio
Entidade: ExpenseReceipt

Campos:

id (UUID ou Long)

cnpj (String)

valor (BigDecimal)

employeeRole (Enum)

status (Enum)

createdAt (LocalDateTime)

deleted (Boolean)

Enum: ExpenseStatus

APROVADO

REJEITADO

AUDITORIA

Enum: EmployeeRole

ESTAGIARIO (limite: 500)

ANALISTA (limite: 2000)

GERENTE (limite: 5000)

6️⃣ Regras de Negócio
Regra 1 — Nota duplicada

Se já existir uma despesa com mesmo CNPJ e valor no mesmo dia → REJEITADO

Regra 2 — Limite por cargo

Se valor > limite do cargo → AUDITORIA

Regra 3 — Fornecedor bloqueado

Se CNPJ estiver na lista de bloqueados → REJEITADO

Regra 4 — Caso válido

Se nenhuma regra crítica for violada → APROVADO

7️⃣ Fluxo da Aplicação

Cliente envia requisição POST /expenses

Controller valida dados básicos

Service aplica regras de auditoria

Status é definido

Entidade é salva no banco

Resposta é retornada ao cliente

8️⃣ Tecnologias Utilizadas

Java 17+

Spring Boot 3

Spring Data JPA

H2 (dev) / PostgreSQL (prod)

Maven

9️⃣ Critério de Conclusão da V1

A versão 1.0 será considerada finalizada quando:

Todas as regras estiverem implementadas

Testes unitários cobrirem as regras principais

Testes de controller estiverem funcionando

README estiver documentado

Projeto rodar sem erro