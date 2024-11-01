# language: pt
Funcionalidade: Autenticação e Agendamento de Coleta
  Como usuário da API
  Quero me autenticar e agendar uma nova coleta
  Para que o registro seja salvo corretamente no sistema

  Cenário: Login bem-sucedido
    Dado que eu tenha as credenciais de usuário e senha:
      | campo    | valor        |
      | usuario  | meu_usuario  |
      | senha    | minha_senha  |
    Quando eu enviar uma requisição de login para o endpoint "/auth"
    Então o status code da resposta deve ser 200
    E o corpo da resposta deve conter um token de autenticação

  Cenário: Agendamento bem-sucedido de coleta com token de autenticação
    Dado que eu esteja autenticado com o token
    E que eu tenha os seguintes dados do agendamento:
      | campo        | valor            |
      | numeroColeta | 1                |
      | descricao    | Coleta de pacote |
      | dataColeta   | 2024-10-23       |
    Quando eu enviar a requisição para o endpoint "/agendamento" de agendamento de coletas
    Então o status code da resposta deve ser 201
