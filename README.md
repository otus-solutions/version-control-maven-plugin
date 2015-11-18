### nexus-maven-plugin ###

Ferramenta utilizada para integrar repositorio Nexus e o versionando do projeto. Utiliza como referencia versões existentes no Servidor Nexus para incrementar a versão do artefato alvo.

Ferramentas utilizadas:
  * [Nexus] (http://www.sonatype.org/nexus/)
  * [Maven Plugin] (http://maven.apache.org/maven-release/maven-release-plugin/)
  
### Listar Versões ###

> mvn version-control:list-artifacts -DurlNexus=http://URL:PORT/nexus -DrepositoryId=ID_RESPOSITORIO

**urlNexus** Endereço Nexus que disponibiliza acesso ao repositorio da consulta

**repositoryID** Identificador do repositorio Nexus

### Atualizar Versão ###

> mvn version-control:update-version -DurlNexus=http://URL:PORT/nexus -DrepositoryId=ID_RESPOSITORIO -Dupgrade=TYPE_UPGRADE

**upgrade** 

Identificador para tipo de upgrade, podendo ser **BUG**, **FEATURE** e **RELEASE**.

Para remover confirmaçõe é possivel utilizar `--batch-mode`.

A atualização segue o seguinte padrão:

`1.2.3-SNAPSHOT`

**Primeiro Identificador** (1) : Release. Será incrementado quando for utilizado o upgrade `RELEASE`.

**Segundo Identificador** (2)  : BUG. Será imcrementado quando for utilizado o upgrade `BUG`.

**Terceiro Identificador** (4) : Feature. Será incrementado quando for utilizado o upgrade `FEATURE`.
 
