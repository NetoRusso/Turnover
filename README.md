# Turnover

![Turnovercapareadme](https://github.com/user-attachments/assets/ec449c61-0e98-4666-a946-c4f206ebffe6)


**Descrição**:Este projeto é um sistema de gestão de funcionários desenvolvido em Java com Spring Boot.
Ele permite o registro, visualização e edição de perfis de funcionários, gerenciamento de departamentos e lotação por carga horária.

## Equipe e Colaboradores

- Argeu do Carmo Russo Neto
- Douglas Daniel Moraes Ferreira
- Pedro Augusto Montes
- Romulo Nogueira de Souza


## Objetivos do Projeto

O objetivo do projeto é desenvolver um sistema de gestão de funcionários que permita registrar, visualizar e editar perfis de funcionários,
gerenciar departamentos e controlar a lotação por carga horária. Além disso, o sistema deve ser responsivo, garantindo uma boa experiência de uso em diferentes dispositivos,
e deve incluir medidas básicas de segurança para proteger os dados dos funcionários. A autenticação será simples, permitindo que administradores acessem o sistema. 

## Escopo do Projeto

O escopo primário do projeto de sistema de gestão de funcionários inclui as principais entregas e funcionalidades que são essenciais para atender aos objetivos do projeto. No caso desse sistema, o escopo primário envolve:

**Registro de Funcionários:**
Sistema de registro com validação de formulário.
Coleta de informações como nome, e-mail, cargo, carga horária e departamento.
Armazenamento dos dados em um banco de dados MySQL/Postgres.
Perfis de Funcionários:
Possibilidade de criar, visualizar e editar perfis de funcionários.
Inclusão de informações básicas, descrição do cargo e histórico de lotação.
Gerenciamento de Departamentos:
CRUD para gerenciar departamentos.
Inclusão de informações detalhadas sobre cada departamento, como nome, localização e descrição.
Armazenamento dos dados no banco de dados.
Lotação por Carga Horária:
Sistema para gerenciar a lotação dos funcionários por carga horária.
Permite criar, visualizar e editar a carga horária e o departamento de cada funcionário.
Inclui histórico de mudanças.
O escopo secundário abrange funcionalidades adicionais que podem não ser essenciais, mas ainda são relevantes para o projeto. Algumas delas podem incluir:

**Autenticação Simples:**
Implementação de um sistema básico de login para administradores.
Não inclui autenticação social ou recuperação de senha por e-mail nesta versão.
Interface Responsiva:
Garantir que a interface se adapte a diferentes dispositivos (computadores, tablets, celulares).
Validação de Dados:
Realizar validação nos formulários para garantir a integridade das informações antes de inseri-las no banco de dados.
Segurança Básica:
Implementar medidas para proteger os dados dos funcionários e prevenir vulnerabilidades conhecidas, como injeção SQL.

## Tecnologias Utilizadas

Vamos integrar cada uma das tecnologias em um projeto full stack de gestão de funcionários. Imagine que estamos construindo um sistema para uma empresa gerenciar seus colaboradores, 
departamentos e histórico de lotação.

**HTML5 (Hypertext Markup Language 5):**
Integração: O HTML5 é a base da nossa aplicação. Usaremos tags HTML para criar a estrutura da página, como cabeçalhos, parágrafos e formulários.
Exemplo: Criaremos um formulário de registro de funcionários com campos para nome, e-mail, cargo e departamento.


**CSS3 (Cascading Style Sheets 3):**
Integração: O CSS3 será responsável pelo estilo visual da nossa aplicação.
Exemplo: Vamos aplicar estilos ao formulário, como cores de fundo, fontes e margens, para torná-lo mais atraente.

**JavaScript:**
Integração: O JavaScript adicionará interatividade à nossa aplicação.
Exemplo: Validaremos os campos do formulário em tempo real, exibindo mensagens de erro se algo estiver incorreto.

**Java com Spring Boot:**
Integração: O Spring Boot nos ajudará a criar a lógica de negócios do sistema.
Exemplo: Criaremos endpoints REST para cada funcionalidade, como adicionar um novo funcionário ou consultar o histórico de lotação.

**PostgreSQL:**
Integração: O PostgreSQL será nosso banco de dados para armazenar informações.
Exemplo: Quando um novo funcionário for registrado, salvaremos seus dados no banco. Também consultaremos o banco para exibir perfis existentes.
Em resumo, essa integração nos permitirá criar um sistema completo, desde a interface visual até a parte funcional e o armazenamento dos dados.

## Arquitetura e Design

<img width="2012" alt="fluxo turnover01" src="https://github.com/user-attachments/assets/13172cc2-49a4-434a-ac34-e5084ce975f0">




## Desenvolvimento e Implementação (Metodologia)

## 1. Planejamento e Definição de Requisitos

**Reunião Inicial:** Definaimos os objetivos do projeto, os requisitos funcionais e não funcionais.
Documentação: Criamos no "Excalidraw" um documento de requisitos detalhado.
Wireframes e Mockups: Desenvolvemos esboços das interfaces principais.

![Imagem do WhatsApp de 2024-07-20 à(s) 00 23 05_8e62e0d5](https://github.com/user-attachments/assets/0a9d53cc-48f4-446c-9b1e-85dcbd9203ac)

**1.1. Momento de entender a dor do mercado para a elaboração do nosso projeto :**

**Debates construtivos:** Identificamos as dores do mercado buscando as principais necessidades do mercado,se existia lacunas no mercado.Fizemos análise de possiveis competidores e funcionalidades reais.Leemos feedbacks de usuarios e tambem a tendência do setor buscando mecanismos inovadores.

![Imagem do WhatsApp de 2024-07-20 à(s) 14 50 12_6e0dc62b](https://github.com/user-attachments/assets/40988a2e-6cee-444d-8a0d-3cf6d90780e2)


## 2. Configuração do Ambiente de Desenvolvimento
**Estrutura de Pastas:** Organize as pastas do projeto de forma lógica:

/project-root
├── /src
│   ├── /main
│   │   ├── /java
│   │   ├── /resources
│   │   └── /Front-End
│   │       ├── /css
│   │       ├── /js
│   │       └── /html
├── /test
└── /database

![Captura de tela 2024-07-21 132904](https://github.com/user-attachments/assets/08b6f637-90ba-4d84-a987-c81ba577c1bc)


**Controle de Versão:** Configuramos um repositório Git para o projeto.

## 3. Desenvolvimento Front-end (Neto e Douglas)

**HTML5 e CSS3:** Criamos as páginas HTML e estilize-as com CSS. Utilize boas práticas de organização de CSS3.

**JavaScript:** Adicione interatividade às páginas com JavaScript.Respeitando o uso do codigo Vanilla Script.

## 4. Desenvolvimento Back-end (Pedro e Romulo)

**Configuração do Spring Boot:** Configure o projeto Spring Boot com as dependências necessárias.

**Modelagem do Banco de Dados:** Definimos no Excalidraw o esquema do banco de dados PostgreSQL.

**Criação de APIs:** Desenvolva as APIs RESTful para comunicação entre o front-end e o back-end.

## 5. Integração e Testes

**Integração Contínua:** Configure pipelines de CI/CD para automação de builds e testes.

**Testes Unitários e de Integração:** Escreva testes para garantir a qualidade do código.

## 6. Deploy e Monitoramento
**Deploy:** Realize o deploy da aplicação em um servidor ou serviço de nuvem.

**Monitoramento:** Implemente ferramentas de monitoramento para acompanhar a performance e a saúde da aplicação.

## 8. Manutenção e Atualizações 
**Feedback e Melhorias:** Coletamos feedback dos usuários e implementamos melhorias contínuas.

**Documentação:** Mantenhemos a documentação do projeto atualizada.


## Demonstração:
Segue o Link:
https://turnover-kappa.vercel.app/

## Conclusão:
Resumo do Projeto Turnover
Descrição: Este projeto é um sistema de gestão de funcionários desenvolvido em Java com Spring Boot. Ele permite o registro, visualização e edição de perfis de funcionários, gerenciamento de departamentos e lotação por carga horária.

## Equipe e Colaboradores: 

Argeu do Carmo Russo Neto,

Douglas Daniel Moraes Ferreira,

Pedro Augusto Montes,

Romulo Nogueira de Souza.

## Objetivos do Projeto:

Registrar, visualizar e editar perfis de funcionários.
Gerenciar departamentos e controlar a lotação por carga horária.
Garantir uma interface responsiva e medidas básicas de segurança.
Implementar autenticação simples para administradores.

## Escopo do Projeto:

**Registro de Funcionários:** Sistema de registro com validação de formulário e armazenamento em banco de dados MySQL/Postgres.

**Perfis de Funcionários:** Criação, visualização e edição de perfis com informações básicas e histórico de lotação.

**Gerenciamento de Departamentos:** CRUD para departamentos com informações detalhadas.

**Lotação por Carga Horária:** Gerenciamento da carga horária e departamento dos funcionários com histórico de mudanças.

**Autenticação Simples:** Login básico para administradores.

**Interface Responsiva:** Adaptação a diferentes dispositivos.

**Validação de Dados:** Garantia da integridade das informações.

**Segurança Básica:** Proteção contra vulnerabilidades como injeção SQL.

**Tecnologias Utilizadas:**

**HTML5:** Estrutura da página.

**CSS3:** Estilo visual.

**JavaScript:** Interatividade.

**Java com Spring Boot:** Lógica de negócios e endpoints REST.
