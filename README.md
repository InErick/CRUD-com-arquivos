# 📑 CRUD SIMPLES

Sistema de gerenciamento de clientes desenvolvido em Java com foco em persistência de dados em sistema de arquivos local. O projeto aplica conceitos de arquitetura em camadas para garantir um código organizado e escalável.

## 📌 Sobre o Projeto
O objetivo deste sistema é realizar o CRUD completo de clientes, onde cada registro é armazenado como um arquivo `.csv` individual. O sistema gerencia automaticamente a criação de pastas, geração de IDs únicos e validação de dados.

## 🌳 Estrutura de Pastas (Packages)
A organização do código segue o padrão de separação de responsabilidades:

```text
src/
├── Entities/
│   └── Client.java              # Representação do objeto e seus atributos.
├── Repositories/
│   ├── ClientRepository.java     # Interface (Contrato) para persistência.
│   └── FileClientRepository.java   # Manipulação de arquivos, leitura e escrita de CSV.
├── Services/
│   └── ClientService.java        # Regras de negócio, validações e fluxo de dados.
└── Main/
    └── Main.java                 # Interface de linha de comando e inicialização.