# Changelog

Todas as mudanças relevantes do projeto serão documentadas aqui.

O formato é baseado no [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
e este projeto segue o [Versionamento Semântico](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
### Changed
### Deprecated
### Removed
### Fixed
### Security
### Quality

## [0.1.0] - 2025-03-09

### Added
- Estrutura inicial de pipeline CI/CD com GitHub Actions
- Execução automatizada de build e testes em eventos de push e pull request
- Build automatizado de imagem Docker
- Publicação automatizada de artefatos de build
- Publicação de imagens no Google Artifact Registry
- Workflows reutilizáveis para automação do ciclo de build

### Security
- Escaneamento automatizado de vulnerabilidades com Trivy

### Quality
- Validação automatizada de qualidade de código com Checkstyle

## [0.2.0] - 2025-03-10

### Added
- Modelagem inicial do domínio da aplicação de gestão bibliotecária
- Enumerações para controle de estados de negócio
- Configuração inicial da persistência da aplicação

## [0.3.0] - 2025-03-26

### Changed
- Simplificada a modelagem do domínio, consolidando entidades para reduzir complexidade arquitetural
- Refatorada a modelagem de empréstimos com remoção da entidade intermediária de itens
- Padronizado o controle de estados dos registros entre entidades

## [0.4.0] - 2025-03-31

### Added
- CRUD inicial para gerenciamento de usuários
- CRUD inicial para gerenciamento de livros
- CRUD inicial para gerenciamento de multas
- CRUD inicial para gerenciamento de empréstimos
- Sistema inicial de reservas
- Endpoints para consulta de relacionamentos entre usuários, empréstimos e reservas
- Ativação e desativação de usuários
- Ativação e desativação de livros
- Pagamento de multas

### Changed
- Padronizado o gerenciamento transacional nas operações de negócio

## [0.5.0] - 2025-04-14

### Added
- Tratamento global de exceções para respostas consistentes da API
- Validações de entrada para entidades e operações
- DTO dedicado para atualizações parciais
- Parâmetros opcionais para filtros e consultas

### Changed
- Refatorada a arquitetura da API para utilização de DTOs entre camadas
- Reorganizada a estrutura da aplicação por casos de uso
- Extraído o mapeamento entre entidades e contratos da API
- Refatorado o domínio para encapsular regras de negócio nas entidades

## [0.6.0] - 2025-04-15

### Added
- Suporte à negociação de conteúdo para respostas HTTP da API

### Changed
- Padronizado o tratamento de erros da API conforme RFC 7807
- Melhorada a consistência do tratamento de exceções

## [0.7.0] - 2025-04-24

### Added
- Sistema completo de reservas
- Cancelamento de reservas
- Cancelamento de pagamentos
- Controle de estoque integrado ao fluxo de empréstimos
- Validações para impedir operações inconsistentes
- Scheduler para atualização automática de empréstimos atrasados e reservas expiradas

### Changed
- Atualizado o fluxo de criação de empréstimos para exigir associação explícita entre usuários e livros

## [0.8.0] - 2025-05-24

### Added
- Deploy automatizado da aplicação no Google Cloud Run
- Configuração inicial da infraestrutura GCP
- Integração da aplicação com MySQL
- Perfil dedicado para testes automatizados com H2

### Changed
- Ajustado datasource para compatibilidade com ambientes cloud

### Security
- Substituído o uso de credenciais estáticas do Google Cloud por Workload Identity Federation

## [1.0.0] - Pre-Microservices Baseline

### Changed
- Consolidação da versão final da arquitetura monolítica antes da migração para microsserviços