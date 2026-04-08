# language: pt

@login @e2e
Funcionalidade: Login
  Como usuário do WhatsNext
  Quero acessar minha conta
  Para gerenciar minhas tarefas

  Contexto:
    Dado que estou na página de login

  @smoke
  Cenario: Login com credenciais validas
    E preencho o usuario "valido"
    E preencho a senha "valida"
    E clico no botão de login
    Então devo ser redirecionado para o dashboard

  Esquema do Cenario: Login com credenciais invalidas
    E preencho o usuario "<usuario>"
    E preencho a senha "<senha>"
    E clico no botão de login
    Entao devo ver a mensagem de erro "<mensagem>"
    E devo permanecer na pagina de login
    Exemplos:
      |usuario|senha|mensagem|
      |invalido|invalida|Usuário ou senha incorretos.|
      |valido    |invalida|Usuário ou senha incorretos.|

  Esquema do Cenario: Login com campos em branco
    E preencho o usuario "<usuario>"
    E preencho a senha "<senha>"
    E clico no botão de login
    Entao devo permanecer na pagina de login
    Exemplos:
      |usuario|senha|
      |       |     |
      |invalido|    |
      |        |invalida|

  Cenario: Alternar visibilidade da senha
    E preencho a senha "senha"
    Entao o campo de senha deve estar com tipo "password"
    Quando clico no botão de alternar visibilidade
    Entao o campo de senha deve estar com tipo "text"
    Quando clico no botão de alternar visibilidade
    Entao o campo de senha deve estar com tipo "password"
