package br.org.ccem.maven.goals;


import br.org.ccem.maven.dto.GitNotes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;

@Mojo(name = "integrate")
public class IntegrateMojo extends AbstractMojo {

    @Parameter(property = "commitType", required = true)
    private String commitType;

    @Parameter(property = "remote", required = true)
    private String remote;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            GitNotes gitNotes = new GitNotes();

            printData(gitNotes.fetchNotes(Runtime.getRuntime(), remote).getInputStream());
            printData(gitNotes.pullNotes(Runtime.getRuntime(), remote).getInputStream());
            printData(gitNotes.createNote(Runtime.getRuntime(), commitType).getInputStream());
            printData(gitNotes.pushNote(Runtime.getRuntime(), remote).getInputStream());

        } catch (IOException e) {
            getLog().error(e);
        }
    }

    private void printData(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));

        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                getLog().info(line);
            }
        } catch (IOException e) {
            getLog().error(e);
        }

    }
}
