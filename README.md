### nexus-maven-plugin ###

Ferramenta utilizada para integrar repositorio Nexus e o versionando do projeto. Utiliza como referencia versões existentes no Servidor Nexus para incrementar a versão do artefato alvo.

Ferramentas utilizadas:
  * [Nexus] (http://www.sonatype.org/nexus/)
  * [Maven Plugin] (http://www.mojohaus.org/versions-maven-plugin/)
  
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

**PS: Caso seja executado em um projeto raiz com multi modulos, deve ser utilizada a opção `-N` para evitar a recursão.**

### Redução de Prefixo ###

Para utilização do plugin com seu respectivo shortname é preciso adicionar seu plugin group. Editar o arquivo settings.xml dentro da pasta .m2.

```xml
 <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
	http://maven.apache.org/xsd/settings-1.0.0.xsd">
	<pluginGroups>
		<pluginGroup>br.org.ccem.maven</pluginGroup>
	</pluginGroups>
</settings>
```
 
