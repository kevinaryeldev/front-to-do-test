# language: pt

@dashboard @e2e
Funcionalidade: Dashboard
  Como usuário autenticado do WhatsNext
  Quero gerenciar minhas tarefas
  Para organizar minha rotina

  Contexto:
    Dado que estou logado no dashboard

  @smoke
  Cenário:  Dashboard vazio ao iniciar
    Então devo ver o estado vazio
    E o total de tarefas deve ser 0

  Esquema do Cenário: Criar tarefa por nível de urgência
    Quando crio uma tarefa com título "Tarefa <urgencia>" nota "<nota>" e urgência "<urgencia>"
    Então devo ver 1 tarefa na lista
    E a primeira tarefa deve ter o título "Tarefa <urgencia>"
    E a primeira tarefa deve ter urgência "<urgencia>"
    E o total de tarefas deve ser 1
    E as tarefas pendentes devem ser 1
    Exemplos:
      | urgencia | nota              |
      | baixa    |                   |
      | media    |                   |
      | alta     | Focar em waits    |
      | urgente  | Resolver hoje!    |

  Cenário: Falha ao criar tarefa sem título
    Quando abro o drawer de nova tarefa
    E submeto o formulário sem preencher o título
    E fecho o drawer
    Então devo ver o estado vazio

  Cenário: Criar múltiplas tarefas
    Quando crio uma tarefa com título "Tarefa 1" nota "" e urgência "baixa"
    E crio uma tarefa com título "Tarefa 2" nota "" e urgência "media"
    E crio uma tarefa com título "Tarefa 3" nota "" e urgência "urgente"
    Então devo ver 3 tarefas na lista
    E o total de tarefas deve ser 3
    E as tarefas pendentes devem ser 3
    E as tarefas urgentes devem ser 1

  Cenário: Marcar tarefa como concluída
    Quando crio uma tarefa com título "Tarefa para concluir" nota "" e urgência "media"
    E marco a primeira tarefa como concluída
    Então a primeira tarefa deve estar concluída
    E as tarefas concluídas devem ser 1
    E as tarefas pendentes devem ser 0

  Cenário: Desmarcar tarefa concluída
    Quando crio uma tarefa com título "Tarefa toggle" nota "" e urgência "baixa"
    E marco a primeira tarefa como concluída
    E marco a primeira tarefa como concluída
    Então a primeira tarefa não deve estar concluída
    E as tarefas pendentes devem ser 1

  Cenário: Deletar uma tarefa
    Quando crio uma tarefa com título "Tarefa para deletar" nota "" e urgência "baixa"
    E deleto a primeira tarefa
    Então devo ver o estado vazio
    E o total de tarefas deve ser 0

  Esquema do Cenário: Filtrar tarefas por nível de urgência
    Quando crio uma tarefa com título "Tarefa <urgencia>" nota "" e urgência "<urgencia>"
    E filtro por "<urgencia>"
    Então devo ver 1 tarefa na lista
    Quando filtro por "all"
    Então devo ver 1 tarefa na lista

    Exemplos:
      | urgencia |
      | baixa    |
      | media    |
      | alta     |
      | urgente  |

  Cenário: Filtrar entre pendentes e concluídas
    Quando crio uma tarefa com título "Pendente" nota "" e urgência "media"
    E crio uma tarefa com título "Para concluir" nota "" e urgência "alta"
    E marco a primeira tarefa como concluída
    E filtro por "done"
    Então devo ver 1 tarefa na lista
    Quando filtro por "pending"
    Então devo ver 1 tarefa na lista
    Quando filtro por "all"
    Então devo ver 2 tarefas na lista

  Cenário: Fazer logout
    Quando faço logout
    Então devo ser redirecionado para a página de login

  Cenário: Redirecionar para login ao acessar dashboard sem token
    Quando limpo o localStorage e acesso o dashboard
    Então devo ser redirecionado para a página de login
