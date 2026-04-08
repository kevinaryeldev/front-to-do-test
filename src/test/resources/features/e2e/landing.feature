# language: pt

@landing @e2e
Funcionalidade: Landing Page
  Como visitante do WhatsNext
  Quero visualizar a página inicial
  Para conhecer o produto e acessar minha conta

  @smoke
  Cenario: Exibir botão Entrar quando não autenticado
    Dado que estou na landing page sem autenticação
    Entao devo ver o botão "Entrar" na navbar

  Cenario: Botão na Hero redireciona para login
    Dado que estou na landing page sem autenticação
    Quando clico no botão "Entrar" do hero
    Entao devo ser redirecionado para a página de login

  Cenario: Botão no CTA redireciona para login
    Dado que estou na landing page sem autenticação
    Quando clico no botão "Entrar agora" do CTA
    Entao devo ser redirecionado para a página de login

  Cenario: Exibir botão Dashboard quando autenticado
    Dado que estou na landing page autenticado
    Entao devo ver o botão "Ir para o Dashboard" na navbar