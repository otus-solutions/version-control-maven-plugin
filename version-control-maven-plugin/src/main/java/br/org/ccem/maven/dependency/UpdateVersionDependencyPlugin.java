package br.org.ccem.maven.dependency;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

public class UpdateVersionDependencyPlugin {

    private MavenProject mavenProject;
    private MavenSession mavenSession;
    private BuildPluginManager buildPluginManager;

    public UpdateVersionDependencyPlugin(MavenProject mavenProject, MavenSession mavenSession, BuildPluginManager buildPluginManager) {
        this.mavenProject = mavenProject;
        this.mavenSession = mavenSession;
        this.buildPluginManager = buildPluginManager;
    }

    public void call(String version) throws MojoExecutionException {
        executeMojo(
                plugin(
                        groupId("org.codehaus.mojo"),
                        artifactId("versions-maven-plugin"),
                        version("2.2")
                ),
                goal("set"),
                configuration(
                        element(name("newVersion"), version),
                        element(name("generateBackupPoms"), "false")
                ),
                executionEnvironment(
                        mavenProject,
                        mavenSession,
                        buildPluginManager
                )
        );
    }
}
