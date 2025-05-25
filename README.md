
# Gestão de Tarefas

## Descrição
Este projeto é uma aplicação Java Web desenvolvida utilizando JavaServer Faces (JSF) e persistência de dados com PostgreSQL e JPA/HIBERNATE. O objetivo principal é demonstrar a integração entre essas tecnologias, realizando o cadastro, edição, remoção e busca de tarefas.
## Funcionalidades implementadas

- (A) Foi desenvolvido um sistema de gestão de tarefas utilizando JSF e o padrão de modelo MVC, persistindo as informações em um banco de dados.

- (B) Foi realizada a persistência de dados no banco de dados PostgreSQL utilizando o mecanismo JPA/HIBERNATE do Java.

- (C) Foi utilizado o JPA/HIBERNATE para o mapeamento objeto-relacional, como também para gerenciar as consultas personalizadas e as operações no banco de dados, como INSERT, DELETE e UPDATE. 

- (F) Foi utilizado o Maven para gerenciar as dependências utilizadas no projeto e adicionado novas funções na listagem como a opção de visualizar as tarefas e a contagem de tarefas concluídas e em andamento.


## Como configurar

### 1 - Clonar o repositório
Clone o projeto utilizando:

```bash
  git clone https://github.com/wermesson-silva/projeto-esig.git
```

### 2 - Configurar o banco de dados
- Crie um banco no PostgreSQL chamado: gestao_tarefas

### 3 - Importar o projeto
- Baixe o eclipse IDE
- Após baixá-lo selecione as opções:
```bash
 File > Import > Maven > Existing Maven Projects.
 ```
- Selecione o caminho onde o projeto foi clonado

### 4 - Configurar Servidor
- Crie um servidor Wildfly 23 ou importe o servidor já configurado disponível em [WildFly-23.0.2.Final](https://drive.google.com/drive/folders/1sGG37jGv9AvJRKIbaln_gdXvxza9d6VQ?usp=drive_link)

Para que o WildFly 23.0.2.Final se comunique com um banco de dados PostgreSQL, é necessário instalar o driver JDBC criando um módulo personalizado. Primeiro, navegue até o diretório de módulos do WildFly: WildFly-23.0.2.Final/modules/system/layers/base/. Em seguida, crie a seguinte estrutura de diretórios: org/postgresql/main/. Dentro da pasta main, coloque o driver JDBC do PostgreSQL (por exemplo, postgresql-42.7.2.jar, que pode ser baixado no site oficial ou em [WildFly-23.0.2.Final](https://drive.google.com/drive/folders/1sGG37jGv9AvJRKIbaln_gdXvxza9d6VQ?usp=drive_link)). Depois, crie um arquivo chamado module.xml na mesma pasta (main) com o seguinte conteúdo:

```
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.5" name="org.postgresql">
  <resources>
    <resource-root path="postgresql-42.7.2.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

Certifique-se de que o nome do arquivo no atributo path corresponde exatamente ao nome do arquivo .jar copiado. Ao final, sua estrutura de diretórios deve estar assim:
```text
org/
└── postgresql/
    └── main/
        ├── postgresql-42.7.2.jar
        └── module.xml
```
- Configure o arquivo standalone.xml com as informações do banco de dados criado e sua senha (você pode ver o arquivo standalone configurado em [WildFly-23.0.2.Final](https://drive.google.com/drive/folders/1sGG37jGv9AvJRKIbaln_gdXvxza9d6VQ?usp=drive_link))

### 5 - Executar
- Adicione o projeto no servidor
- Inicie o servidor no eclipse
- Abra o navegador e digite a seguinte url:
```bash
http://localhost:8080/gestao-tarefas/
```
Pronto, após as instruções a aplicação estará pronta e funcionando.
 

    