# SmartExpense API 

SmartExpense é um sistema backend em desenvolvimento para **auditoria inteligente de reembolsos financeiros**, utilizando uma arquitetura moderna com **Java (Spring Boot)** e **Python (FastAPI)**.

O objetivo do projeto é automatizar a análise de notas fiscais enviadas por usuários, aplicando regras de negócio para detectar **erros, duplicidades ou possíveis fraudes**.

---

## 🚀 Status do Projeto

🔧 **Em desenvolvimento (fase inicial)**  
Atualmente o projeto contém a estrutura base do backend Java e um endpoint de verificação de saúde da aplicação.

---

## 🧠 Visão Geral da Arquitetura

O sistema será composto por dois serviços principais:

- **SmartExpense API (Java / Spring Boot)**  
  Responsável por:
    - Orquestração do fluxo
    - Regras de negócio e auditoria
    - Persistência de dados
    - Segurança e validações

- **SmartExpense FastAPI (Python / FastAPI)**  
  Responsável por:
    - Processamento de imagens (OCR)
    - Extração de dados de notas fiscais
    - Retorno de dados estruturados com grau de confiança

A comunicação entre os serviços será feita via **REST API (JSON)**.

---

## 📌 Funcionalidades Planejadas

- Upload de imagens de notas fiscais (JPG / PNG)
- Extração automática de:
    - CNPJ
    - Data de emissão
    - Valor total
- Regras de auditoria automática
- Detecção de duplicidade de reembolso
- Classificação de notas:
    - APROVADO
    - REVISÃO_MANUAL
    - REJEITADO
- Relatórios automáticos de transações suspeitas

---

## 🛠️ Tecnologias Utilizadas

### Backend Core
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 (ambiente de desenvolvimento)
- Maven

### Inteligência / OCR (planejado)
- Python 3.10+
- FastAPI
- OCR (Tesseract / EasyOCR)

