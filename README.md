# AA1: Desenvolvimento de um sistema utilizando JSP, Servlet e JDBC

**(UFSCar)**
**Grupo 1: Jorge Pires, Heitor Colichio & Lucas Abbiati**

## Sistema de Entrevistas de Empregos

Um profissional se inscreve para uma entrevista de emprego em uma empresa.

### Atividade AA-1: Sistema para entrevistas de empregos.

O sistema deve possuir um cadastro de *profissionais*, com os seguintes dados: e-mail, senha, CPF, nome, telefone, sexo e data de nascimento.

O sistema deve possuir um cadastro de *empresas*, com os seguintes dados: e-mail, senha, CNPJ, nome e cidade.

O sistema deve possuir um cadastro de entrevistas, com os seguintes dados: CPF do profissional, CNPJ da empresa e data/hora da entrevista. Assume-se que a druação da entrevista é de 30 minutos e sempre inicia-se em "hora cheia" (14h 00min etc) ou “hora meia” (14h 30min etc).

O sistema deve atender aos seguintes requisitos:
- R1: CRUD de empresas  (requer login de administrador)
- R2: CRUD de profissionais (requer login de administrador)
- R3: Listagem de todos as empresas em uma única página (não requer login)
- R4: Listagem de todos as empresas por cidade (não requer login)
- R5: Agendamento de entrevista com uma empresa (requer login do profissional via email + senha). Depois de fazer login, o profissional pode se inscrever em uma entrevista. Para isso, deve escolher uma empresa (escolhendo a partir de uma lista), uma data horário, e deve ser gravado a entrevista na base de dados. Após a efetivação do agendamento da entrevista, o profissional e a empresa devem ser informados (via e-mail)sobre o agendamento realizado.
- R6: Listagem de todas as inscrições de um profissional (requer login do profissional via e-mail + senha). Depois de fazer login, o profissional pode visualizar todas as suas inscrições para entrevista gravadas.
- R7: Listagem de todas as entrevistas de uma empresa (requer login da empresa via e-mail + senha). Depois de fazer login, a empresa pode visualizar todas as suas entrevistas cadastradas.
- R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.

O sistema deve tratar todos os erros possíveis(cadastro duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console, em arquivo ou na base de dados.

**Arquitetura:** Modelo-Visão-Controlador

**Tecnologias**
- Servlet, JSP, JSTL & JDBC (Lado Servidor)
- Javascript & CSS (Lado Cliente)

**Ambiente de Desenvolvimento**
- A compilação e o deployment deve ser obrigatoriamente ser realizado via maven.
- Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório (preferencialmente github).