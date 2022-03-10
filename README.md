# Sistema de Usuarios
Exercício da disciplina de Projeto de Sistemas de Software
## Gerar .jar
```bash
mvn clean install
```
## Descrição do minimundo
Ao fazer acesso o primeiro usuário realizará seu cadastro e torna-se o administrador. Ele poderá cadastrar outros usuários. Outros usuários não cadastrados, 
aqui chamados “Não usuários” podem fazer seu cadastro (Criar usuário), entretanto,  precisam aguardar a autorização do administrador para fazer acesso ao sistema.
Todos os usuários devem fazer acesso ao sistema com nome de usuário e senha (autenticação).

Os administradores podem enviar mensagens ou notificações para um ou mais usuários cadastrados no sistema. Ao acessar o sistema os usuários devem ter um meio de visualizar
as notificações que devem ser marcadas como “lida”.

Ao listar os usuários o administrador deve visualizar, além do nome e data de cadastro, o número de notificações enviadas e o número de notificações lidas.

A qualquer momento, desde que autenticados, cada usuário poderá realizar a alteração da sua senha.

O sistema deve realizar o registro em arquivo (log de sistema) para as  seguintes operações:
- Inclusão, alteração ou exclusão de usuários;
- Envio de notificações;
- Leitura de notificações;
- Alteração de senha;
- Autorização de usuário.
As mensagens gravadas no arquivo de log devem ser desta forma:

Exemplos:

\"\<OPERACAO\>\: \<\<NOME\>\>, \(\<\<DATA\>\>, \<\<HORA\>\>, e \<\<USUARIO\>\>\"
  
Caso as operações falhem:  Ocorreu a falha \<\<MENSAGEM DA FALHA\>\> ao realizar a \"\<OPERACAO\> do contato \<\<NOME\>\>, \(\<\<DATA\>\>, \<\<HORA\>\>, e \<\<USUARIO\>\>.\"

Substituir as variáveis entre pelos respectivos dados.
- \<\<NOME\>\> = nome do contato sob o qual a operação está sendo realizada
- \<\<DATA\>\> = data atual do sistema operacional, no formato DD/MM/AAAA, onde D é dia, M – mês, e A – ano.
- \<\<HORA\>\> = hora atual do sistema operacional, no formato HH:MM:SS, onde HH é a hora, MM -  minuto e S segundos.
- \<\<USUARIO\>\> = usuário autenticado no sistema
- \<\<MENSAGEM\>\> = mensagem de falha no sistema
- \<\<OPERAÇÃO\>\> = Operações listadas acima
Deve ser disponibilizada uma tela de "Configuração" que permita escolher dentre os formatos de arquivos de log, o que será usado na aplicação. O usuário poderá mudar o 
tipo de log em tempo de execução. A escolha feita pelo usuário deverá ser persistida.

Exibir no rodapé do sistema:
- Usuário logado
- Tipo {Usuário, Administrador}
- Botão -  quanto existirem notificações, cujo texto deve ser o total de notificações. Ao clicar deve ser aberta uma janela onde o usuário visualiza e acessa o 
que for indicado nas notificações
  
## Requisitos não funcionais
- RNF01 – Casos de uso do tipo CRUD com State
Os casos de uso do tipo CRUD devem seguir o que foi ministrado e debatido em aula
- RNF02 – Persistência de dados
Utilize como persistência qualquer banco de dados, relacional ou não, que não exija instalações ou configurações extras no ambiente de implementação. Prefira utilizar o SQLite.
- RNF03 – Padrão de projeto DAO
Utilize o padrão de projeto DAO, sabendo que não é permitido utilizar Framework de persistência como  Hibernate ou similares.
- RNF04 – Interface gráfica
O sistema deve ser desenvolvido utilizando o kit de componentes gráficos do Java Swing.
O sistema deve permitir que usuários naveguem entre telas que estejam abertas para realizar as funções desejadas. Ou seja, utilizar o MDI (Multiple Document Interface).
- RNF05 – Utilizar o padrão de projeto Command
Ao utilizar o State, utilizar o padrão Command integrado à ele.
- RNF06 – Padrão de projeto Observer
Implemente o padrão Observer de modo que caso qualquer usuário altere algum dado em uma janela aberta, as demais janelas abertas referentes àquele dado sejam atualizadas.
- RNF07 – Formatos de arquivos de Log
O sistema deve permitir os seguintes formatos para arquivos de log: CSV (com separação de campos usando ponto e vírgula “;”), JSON e XML. 
- RNF08 – Padrão de Projeto Adapter para Log
O módulo de log deve adotar o padrão de projeto Adapter. Além disso, deve ser um projeto Maven independente, utilizado pelo sistema proposto nesta especificação.
- RNF09 – Criação de senhas
Ao criar senhas do sistema utilizar o [Validador](https://github.com/claytonfraga/validadorsenha) de senhas.