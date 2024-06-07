# Easy Tech
Nossa plataforma será uma ferramenta multifuncional, abordando não apenas a identificação de pontos de poluição, mas também incluindo recursos de conscientização pública e denúncia de incidentes. Através de gráficos e mapas interativos, os usuários poderão visualizar claramente as áreas mais afetadas pela poluição, incentivando ações proativas para limpeza e prevenção.
## Integrantes

- MATHEUS MATOS - RM:99792
- KAREN VITORIA JESUS DA SILVA - RM:99468
- JULIANNY ARAUJO PEREIRA - RM:99554
- DIEGO HENRIQUE SANTOS DE OLIVEIRA - RM:550269
- JULIA DE FATIMA QUEIROZ - RM:551130

## Instruções para uso

### Configuração do Spring
- site: https://start.spring.io/
  ![Configuração Spring](https://github.com/diegohs13/GS_JAVA/blob/main/configSpringGs.png)
- application.properties
  ![Configuração application.properties](https://github.com/diegohs13/GS_JAVA/blob/main/appProp.png)

### Import insominia
- insomnia v4
- Importar arquivo
  ![Importar arquivo](https://github.com/diegohs13/GS_JAVA/blob/main/importInsomnia.png)
  ![Import completo](https://github.com/diegohs13/GS_JAVA/blob/main/importCompleto.png)
- Importe o arquivo InsomniaSP2.json para o Insomnia

### Execução
Após a importação do arquivo InsomniaSP2.json e a configuração do application.properties, o usuario pode rodar o Sprint2JavaApplication dentro do projeto java.
![Execução](https://github.com/diegohs13/GS_JAVA/blob/main/runGs.png)
![Execução completa](https://github.com/diegohs13/GS_JAVA/blob/main/runGs2.png)

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
USUARIOS
- GET `/usuarios` - Retorna a lista de usuarios cadastrados no sistema.
- POST `/usuarios` - Cadastra um novo usuario no sistema.
- GET `/usuarios/{id}` - Retorna os dados de um usuario específico.
- PUT `/usuarios/{id}/atualizarSenha` - Atualiza a senha de um usuario específico.
- DELETE `/usuarios/{id}` - Deleta um usuario específico.
- POST `/usuarios/{id}/login` - Realiza o login de um usuario no sistema.

RELATORIOS
- GET `/relatorios` - Retorna a lista de exames relatorios no sistema.
- GET `/relatorios/{id}` - Retorna os dados de um relatorio específico.
- PUT `/relatorios/{id}/atualizarDescricao` - Atualiza a descrição de um relatorio específico.
- DELETE `/relatorios/{id}` - Deleta um relatorio específico.
- POST `/relatorios` - Cadastra um novo relatorio no sistema.

LOCALIDADES
- GET `/Localidades` - Retorna a lista de Localidades cadastradas no sistema.
- GET `/Localidades/{id}` - Retorna os dados de uma Localidade específica.
- PUT `/Localidades/{id}/atualizarNome` - Atualiza o nome de uma Localidade específica.
- DELETE `/Localidades/{id}` - Deleta uma Localidade específica.
- POST `/Localidades` - Cadastra uma nova Localidade no sistema.

INTERVENCOES
- GET `/intervencoes` - Retorna a lista de intervencoes cadastradas no sistema.
- GET `/intervencoes/{id}` - Retorna os dados de uma intervencao específica.
- PUT `/intervencoes/{id}/atualizarResiduos` - Atualiza a quantidade de residuos de uma intervencao específica.
- DELETE `/intervencoes/{id}` - Deleta uma intervencao específica.
- POST `/intervencoes` - Cadastra uma nova intervencao no sistema.


DENUNCIAS
- GET `/denuncias` - Retorna a lista de denuncias cadastradas no sistema.
- GET `/denuncias/{id}` - Retorna os dados de uma denuncia específica.
- PUT `/denuncias/{id}/atualizarDescricao` - Atualiza a descrição de uma denuncia específica.
- DELETE `/denuncias/{id}` - Deleta uma denuncia específica.
- POST `/denuncias` - Cadastra uma nova denuncia no sistema.


CONSCIENTIZACOES
- GET `/conscientizacoes` - Retorna a lista de conscientizacoes cadastrados no sistema.
- GET `/conscientizacoes/{id}` - Retorna os dados de uma conscientizacao específica.
- PUT `/conscientizacoes/{id}/atualizarDescricao` - Atualiza a descricao conscientizacao específica.
- DELETE `/conscientizacoes/{id}` - Deleta uma conscientizacao específica.
- POST `/conscientizacoes` - Cadastra uma nova conscientizacao no sistema.


## Imagens
![Diagrama de relacionamentos](https://github.com/diegohs13/GS_JAVA/blob/main/bdRelation.png)
- _**Entidades e Atributos**_<br>
  <br>

- **Usuario**
- id_usuario (chave primária)
- nome
- email
- senha
- tipo_usuario<br>
  <br>

- **Relatoeio**
- id_relatorio (chave primária)
- data_relatorio
- descricao
- quantidade_residuo
- Usuario_id_usuario (chave estrangeira)<br>
  <br>

- **Localidade**
- id_local (chave primária)
- nome_local
- latitude 
- longitude 
- Relatorio_id_relatorio (chave estrangeira)<br>
  <br>

- **Intervencoes**
- id_intervencao (chave primária)
- data_intervencao 
- quantidade_residuos
- numero_pessoas
- Localidade_id_localidade (chave estrangeira)<br>
  <br>

- **Denuncia**
- id_denuncia (chave primária)
- data_denuncia 
- descricao
- status
- Usuario_id_usuario (chave estrangeira)<br>
  <br>

- **Conscienizacao**
- id_campanha (chave primária)
- titulo_campanha
- descricao
- data_inicio
- data_fim<br>
  <br>
<br>
