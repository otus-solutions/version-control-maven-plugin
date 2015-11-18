package br.org.ccem.maven.goals;

import br.org.ccem.maven.dto.NexusSearch;
import br.org.ccem.maven.dto.ProjectData;
import br.org.ccem.maven.exceptions.DataNotFoundException;
import br.org.ccem.maven.rest.ClientRest;
import com.google.gson.Gson;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "list-artifacts")
public class ListArtifactsMojo extends AbstractMojo {
    private static final String URL_SEARCH_NEXUS = "/service/local/lucene/search";

    @Parameter(property = "urlNexus", required = true)
    private String urlNexus;

    @Parameter(property = "repositoryId", required = true)
    private String repositoryId;

    @Parameter(defaultValue = "${project}")
    private MavenProject mavenProject;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        printPreData();

        try {
            ClientRest clientRest = new ClientRest(urlNexus);
            NexusSearch nexusSearch = new Gson().fromJson(fetchData(clientRest), NexusSearch.class);

            for (ProjectData projectData : nexusSearch.getData()) {
                printData(projectData);
            }

        } catch (DataNotFoundException e) {
            printNotFoundedData();
        }
    }

    private void printPreData() {
        getLog().info("===================  Artifacts Into Nexus ==================");
        getLog().info("URL Nexus: " + urlNexus);
        getLog().info("Repository ID: " + repositoryId);
    }

    private void printNotFoundedData() throws MojoFailureException {
        throw new MojoFailureException("Version reference not found. Deploy initial artifact into nexus.");
    }

    private void printData(ProjectData projectData) {
        getLog().info("===============================");
        getLog().info("Group    : " + projectData.getGroupId());
        getLog().info("Artifact :" + projectData.getArtifactId());
        getLog().info("Version  :" + projectData.getVersion());
    }

    private String fetchData(ClientRest clientRest) throws MojoFailureException {
        return clientRest.GET(URL_SEARCH_NEXUS + "?repositoryId="
                + repositoryId +
                "&g=" + mavenProject.getGroupId() +
                "&a=" + mavenProject.getArtifactId());
    }
}
