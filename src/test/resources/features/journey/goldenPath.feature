# language: pt

@goldenPath
Funcionalidade: Jornada completa do usuário
  Como usuário do WhatsNext
  Quero realizar todas as ações do sistema em sequência real
  Para validar o fluxo completo da aplicação de ponta a ponta

  Cenário: Fluxo completo — landing, login, CRUD de tarefas e logout
    Dado que estou na landing page sem autenticação
    Quando clico no botão "Entrar" do hero
    E preencho o usuario "valido"
    E preencho a senha "valida"
    E clico no botão de login
    Então devo ser redirecionado para o dashboard

    Quando crio uma tarefa com título "Tarefa Baixa E2E" nota "" e urgência "baixa"
    E crio uma tarefa com título "Tarefa Urgente E2E" nota "Prioridade máxima" e urgência "urgente"
    Então devo ver 2 tarefas na lista
    E o total de tarefas deve ser 2
    E as tarefas pendentes devem ser 2

    Quando filtro por "urgente"
    Então devo ver 1 tarefa na lista
    Quando filtro por "all"
    Então devo ver 2 tarefas na lista

    Quando marco a primeira tarefa como concluída
    Então a primeira tarefa deve estar concluída
    E as tarefas concluídas devem ser 1
    E as tarefas pendentes devem ser 1

    Quando filtro por "done"
    Então devo ver 1 tarefa na lista

    Quando filtro por "all"
    Então devo ver 2 tarefas na lista

    Quando deleto a primeira tarefa
    Então devo ver 1 tarefa na lista
    E o total de tarefas deve ser 1

    Quando faço logout
    Então devo ser redirecionado para a página de login
