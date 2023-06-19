# AA1: Desenvolvimento de um sistema utilizando JSP, Servlet e JDBC

**(UFSCar)**
**Grupo 1: Jorge Pires, Heitor Colichio & Lucas Abbiati**

## Sistema de Banco de Talentos

Um projeto de extensão se cadastra, assim como os alunos, e ao precisar de um aluno, o projeto de extensão pode marcar um entrevista.

### Atividade AA-1: Sistema para agendamento de entrevistas para projetos de extensão

O sistema deve possuir um cadastro de projetos de extensão, com os seguintes dados: nome do projeto, e-mail e senha.

O sistema deve possuir um cadastro de alunos, com os seguintes dados: nome, sexo, telefone, data de nascimento, e-mail institucional, senha, RA, curso, ano de ingresso.

O sistema deve possuir um cadastro de entrevistas, com os seguintes dados: nome do projeto de extensão, RA do aluno, e data/hora da entrevista. Assume-se que a duração da entrevista é de 30 minutos e sempre inicia-se em "hora cheia" (14h 00 min etc) ou "hora meia" (14h 30min etc).

O sistema deve atender aos seguintes requisitos:
- R1: CRUD de projetos de extensão (requer login de administrador)
- R2: CRUD de alunos (requer login de administrador)
- R3: Listagem de todos os alunos em uma única página (não requer login)
- R4: Listagem de todos os alunos por curso (não requer login)
- R5: Agendamento de entrevista com um aluno (requer login do projeto de extensão via e-mail+senha). Depois de fazer o login, o projeto de extensão pode cadastrar uma entrevista. Para isso, deve escolher um aluno (escolhendo a partir de uma lista), uma data/horário, e deve ser gravado a entrevista na base de dados. Após a efetivação do agendamento da entrevista, o projeto de extensãoo e o aluno devem ser informados (via e-mail) sobre o agendamento realizado. 
- R6: Listagem de todas as entrevistas de um projeto de extensão (requer login do projeto de extensão via e-mail + senha). Depois de fazer o login, o projeto de extensão pode visualizar todas as suas entrevistas.
- R7: O sistema não deve permitir o cadastro de entrevistas de um mesmo aluno ou de um mesmo grupo acadêmico em uma mesma data/horário.
- R8: Listagem de todas as entrevistas de um aluno (requer login do aluno via e-mail + senha). Depois de fazer login, o aluno pode visualizar todas as suas entrevistas gravadas.
- R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.

O sistema deve tratar todos os erros possíveis(cadastro duplicados, problemas técnicos, etc) mostrabdi uma página de erros amigável ao usuário e registrando o erro no console, em arquivo ou na base de dados.

**Arquitetura:** Modelo-Visão-Controlador

**Tecnologias**
- Servlet, JSP, JSTL & JDBC (Lado Servidor)
- Javascript & CSS (Lado Cliente)

**Ambiente de Desenvolvimento**
- A compilaçao e o deployment deve ser obrigatoriamente ser realizado via maven.
- Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório (preferencialmente github).