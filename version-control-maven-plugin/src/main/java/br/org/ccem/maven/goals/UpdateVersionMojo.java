package br.org.ccem.maven.goals;


import br.org.ccem.maven.dependency.UpdateVersionDependencyPlugin;
import br.org.ccem.maven.dto.NexusSearch;
import br.org.ccem.maven.dto.ProjectData;
import br.org.ccem.maven.exceptions.DataNotFoundException;
import br.org.ccem.maven.factory.VersionGroup;
import br.org.ccem.maven.factory.VersionNumberControl;
import br.org.ccem.maven.rest.ClientRest;
import com.google.gson.Gson;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "update-version")
public class UpdateVersionMojo extends AbstractMojo {
    private static final String URL_SEARCH_NEXUS = "/service/local/lucene/search";

    @Parameter(property = "urlNexus", required = true)
    private String urlNexus;

    @Parameter(property = "repositoryId", required = true)
    private String repositoryId;

    @Parameter(property = "upgrade", required = true)
    private VersionGroup upgrade;

    @Parameter(defaultValue = "${project}")
    private MavenProject mavenProject;

    @Component
    private MavenSession mavenSession;

    @Component
    private BuildPluginManager pluginManager;

    @Parameter(property = "nexusPassword", required = true)
    private String nexusPassword;

    @Parameter(property = "nexusUser", required = true)
    private String nexusUser;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        printPreData();

        try {
            ClientRest clientRest = new ClientRest(urlNexus, nexusPassword, nexusUser);

            NexusSearch nexusSearch = new Gson().fromJson(fetchData(clientRest), NexusSearch.class);
            UpdateVersionDependencyPlugin updateVersionDependencyPlugin = new UpdateVersionDependencyPlugin(mavenProject, mavenSession, pluginManager);

            ProjectData lastIntegratedVersion = nexusSearch.getData().get(0);
            printFoundedData(lastIntegratedVersion);

            VersionNumberControl versionNumberControl = new VersionNumberControl(lastIntegratedVersion.getVersion());
            versionNumberControl.upgrade(upgrade);

            updateVersionDependencyPlugin.call(versionNumberControl.getVersion().toString());
        }catch (DataNotFoundException e){
            printNotFoundedData();
        }

    }

    private void printPreData() {
        getLog().info("===================  Update Version ==================");
        getLog().info("URL Nexus: " + urlNexus);
        getLog().info("Repository ID: " + repositoryId);
        getLog().info("Upgrade Type: " + upgrade.toString());
    }

    private void printFoundedData(ProjectData projectData){
        getLog().info("===================  Update Version ==================");
        getLog().info("Last Version: " + projectData.getLatestSnapshot());
    }

    private void printNotFoundedData() throws MojoFailureException {
        throw new MojoFailureException("Version reference not found. Deploy initial artifact into nexus.");
    }

    private String fetchData(ClientRest clientRest) throws MojoFailureException {
        return clientRest.GET(URL_SEARCH_NEXUS + "?repositoryId="
                + repositoryId +
                "&g=" + mavenProject.getGroupId() +
                "&a=" + mavenProject.getArtifactId());
    }
}
