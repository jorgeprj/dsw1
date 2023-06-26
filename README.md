# AA1: Desenvolvimento de um sistema utilizando JSP, Servlet e JDBC

**(UFSCar)**
**Grupo 1: Jorge Pires, Heitor Colichio & Lucas Abbiati**

## Sistema de Vaga de Empregos

Uma empresa cadastra uma vaga de emprego, e ao se interessar o profissional pode se inscrever para uma entrevista

### Atividade AA-1: Sistema para vaga de empregos.

O sistema deve possuir um cadastro de profissionais, com os seguintes dados: e-mail, senha, CPF, nome, telefone e data de nascimento.

O sistema deve possuir um cadastro de empresas, com os seguintes dados: e-mail, senha, CNPJ, nome e cidade.

O sistema deve possuir um cadastro de vagas, com os seguintes dados: nome, descrição, CNPJ da empresa, data fim para inscrições dos candidatos e candidatos inscritos.

O sistema deve possuir um cadastro de inscrições para vagas, com os seguintes dados: CPF do profissional, currículo do profissional, data da inscrição.

O sistema deve atender aos seguintes requisitos:
- R1: CRUD de profissionais (requer login de administrador)
- R2: CRUD de empresas (requer login de administrador)
- R3: Listagem de todos as empresas em uma única página (não requer login)
- R4: Listagem de todos as empresas por cidade (não requer login)
- R5: Cadastro de vagas (requer login da empresa via e-mail+senha). Depois de fazer o login, a empresa pode cadastrar uma vaga. Para isso, deve escolher o nome da vaga, a descrição e a data de inicia/fim para as inscrições dos candidatos e deve ser gravado a vaga na base de dados.
- R6: Inscrição em vagas (requer login do profissional via e-mail+senha). Depois de fazer o login, o profissional pode se inscrever para uma vaga. Para isso, deve escolher o currículo para enviar, e deve ser gravado a inscrição na base de dados. Após a efetivação da inscrição, o profissional e a empresa devem ser informados (via e-mail)sobre a inscrição realizada.
- R7: Listagem de todas as inscrições de um profissional (requer login do profissional via e-mail + senha). Depois de fazer login, o profissional pode visualizar todas as suas inscrições gravadas.
- R8: Listagem de todas as vagas de uma empresa (requer login da empresa via e-mail + senha). Depois de fazer login, a empresa pode visualizar todas as suas vagas cadastradas.
- R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.

O sistema deve tratar todos os erros possíveis(cadastro duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console, em arquivo ou na base de dados.

**Arquitetura:** Modelo-Visão-Controlador

**Tecnologias**
- Servlet, JSP, JSTL & JDBC (Lado Servidor)
- Javascript & CSS (Lado Cliente)

**Ambiente de Desenvolvimento**
- A compilação e o deployment deve ser obrigatoriamente ser realizado via maven.
- Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório (preferencialmente github).