# Easy Tech
Nossa plataforma será uma ferramenta multifuncional, abordando não apenas a identificação de pontos de poluição, mas também incluindo recursos de conscientização pública e denúncia de incidentes. Através de gráficos e mapas interativos, os usuários poderão visualizar claramente as áreas mais afetadas pela poluição, incentivando ações proativas para limpeza e prevenção.## Integrantes

- MATHEUS MATOS - RM:99792
- KAREN VITORIA JESUS DA SILVA - RM:99468
- JULIANNY ARAUJO PEREIRA - RM:99554
- DIEGO HENRIQUE SANTOS DE OLIVEIRA - RM:550269
- JULIA DE FATIMA QUEIROZ - RM:551130

## Instruções para uso

### Configuração do Spring
- site: https://start.spring.io/
  ![Configuração Spring](link)
- application.properties
  ![Configuração application.properties](link)

### Import insominia
- insomnia v4
- Importar arquivo
  ![Importar arquivo](link)
  ![Import completo](link)
- Importe o arquivo InsomniaSP2.json para o Insomnia

### Execução
Após a importação do arquivo InsomniaSP2.json e a configuração do application.properties, o usuario pode rodar o Sprint2JavaApplication dentro do projeto java.
![Execução](link)

### Utilização
O usuario pode cadastrar os seguintes dados no sistema utilizando o metodo POST:
- Usuario
- Relarorio
- Localidade
- Intervencoes
- Denuncia
- Conscientizacao

O usuario pode deletar os seguintes dados no sistema utilizando o metodo DELETE:
- Um usuario pelo id
- Um relatorio pelo id
- Uma localidade pelo id
- Uma intervencao pelo id
- Uma denuncia pelo id
- Uma conscientizacao pelo n_protocolo

O usuario pode visualizar os seguintes dados no sistema utilizando o metodo GET:
- Lista de todos os usuarios e de um usuario específico
- Lista de todos os relatorios e de um relatorio específico
- Lista de todas as localidades e de uma localidade específica
- Lista de todas as intervencoes e de uma intervencao específica
- Lista de todas as denuncias e de uma denuncia específica
- Lista de todos os conscientizacoes e de um conscientizacao específico


O usuario pode atualizar os seguintes dados no sistema utilizando o metodo PUT:
- Senha do usuario
- Descrição da relatorio
- Nome da localidade
- Quantidade de residuos da intervenção
- Decrição da denuncia
- Descrição da conscientizacao

O usuario tambem pode realizar o login no sistema com os seguintes dados utilizando o metodo POST:
- id
- senha

## Endpoints
PACIENTES
- GET `/usuarios` - Retorna a lista de usuarios cadastrados no sistema.
- POST `/usuarios` - Cadastra um novo usuario no sistema.
- GET `/usuarios/{id}` - Retorna os dados de um usuario específico.
- PUT `/usuarios/{id}/atualizarSenha` - Atualiza a senha de um usuario específico.
- DELETE `/usuarios/{id}` - Deleta um usuario específico.
- POST `/usuarios/{id}/login` - Realiza o login de um usuario no sistema.

EXAMES
- GET `/relatorios` - Retorna a lista de exames relatorios no sistema.
- GET `/relatorios/{id}` - Retorna os dados de um relatorio específico.
- PUT `/relatorios/{id}/atualizarDescricao` - Atualiza a descrição de um relatorio específico.
- DELETE `/relatorios/{id}` - Deleta um relatorio específico.
- POST `/relatorios` - Cadastra um novo relatorio no sistema.

RECEITAS
- GET `/Localidades` - Retorna a lista de Localidades cadastradas no sistema.
- GET `/Localidades/{id}` - Retorna os dados de uma Localidade específica.
- PUT `/Localidades/{id}/atualizarNome` - Atualiza o nome de uma Localidade específica.
- DELETE `/Localidades/{id}` - Deleta uma Localidade específica.
- POST `/Localidades` - Cadastra uma nova Localidade no sistema.

UNIDADES
- GET `/intervencoes` - Retorna a lista de intervencoes cadastradas no sistema.
- GET `/intervencoes/{id}` - Retorna os dados de uma intervencao específica.
- PUT `/intervencoes/{id}/atualizarResiduos` - Atualiza a quantidade de residuos de uma intervencao específica.
- DELETE `/intervencoes/{id}` - Deleta uma intervencao específica.
- POST `/intervencoes` - Cadastra uma nova intervencao no sistema.


CONSULTAS
- GET `/denuncias` - Retorna a lista de denuncias cadastradas no sistema.
- GET `/denuncias/{id}` - Retorna os dados de uma denuncia específica.
- PUT `/denuncias/{id}/atualizarDescricao` - Atualiza a descrição de uma denuncia específica.
- DELETE `/denuncias/{id}` - Deleta uma denuncia específica.
- POST `/denuncias` - Cadastra uma nova denuncia no sistema.


AGENDAMENTOS
- GET `/conscientizacoes` - Retorna a lista de conscientizacoes cadastrados no sistema.
- GET `/conscientizacoes/{id}` - Retorna os dados de uma conscientizacao específica.
- PUT `/conscientizacoes/{id}/atualizarDescricao` - Atualiza a descricao conscientizacao específica.
- DELETE `/conscientizacoes/{id}` - Deleta uma conscientizacao específica.
- POST `/conscientizacoes` - Cadastra uma nova conscientizacao no sistema.


## Imagens
![Diagrama de relacionamentos](link)
- _**Entidades e Atributos**_<br>
  <br>

- **Paciente**
- cpf (chave primária)
- nome_completo
- data_nasc
- end_paciente
- tel_paciente
- email_paciente<br>
  <br>

- **Clínica**
- cnpj (chave primária)
- nome_clinica
- cel_clinica
- conveniada<br>
  <br>

- **Consulta**
- id_consulta (chave primária)
- data_hora_consulta
- idClinica (chave estrangeira)
- idPaciente (chave estrangeira)
- idAgendamento (chave estrangeira)<br>
  <br>

- **Exames**
- idExame (chave primária)
- idConsulta (chave estrangeira)
- tipo_exame
- resultado<br>
  <br>

- **Agendamento**
- n_protocolo (chave primária)
- idPaciente (chave estrangeira)
- dataHora_agendamento
- clinica_agendamento<br>
  <br>
  <br>

- **_Relacionamentos_**<br>
  <br>

- **Paciente para Consulta**
- Um paciente pode ter várias consultas.
- Uma consulta é específica de um paciente.<br>
  <br>

- **Paciente para Agendamento**
- Um paciente pode ter vários agendamentos.
- Um agendamento é específico de um paciente.<br>
  <br>

- **Consulta para Exames**
- Uma consulta pode ter vários exames associados.
- Um exame é específico de uma consulta.<br>
  <br>

- **Clínica para Consulta**
- Uma clínica pode hospedar várias consultas.
- Uma consulta acontece em uma clínica.<br>