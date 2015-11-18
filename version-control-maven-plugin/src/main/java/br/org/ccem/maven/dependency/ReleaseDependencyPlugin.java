package br.org.ccem.maven.dependency;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

public class ReleaseDependencyPlugin {

    private MavenProject mavenProject;
    private MavenSession mavenSession;
    private BuildPluginManager buildPluginManager;

    public ReleaseDependencyPlugin(MavenProject mavenProject, MavenSession mavenSession, BuildPluginManager buildPluginManager) {
        this.mavenProject = mavenProject;
        this.mavenSession = mavenSession;
        this.buildPluginManager = buildPluginManager;
    }

    public void call(String version) throws MojoExecutionException {
        executeMojo(
                plugin(
                        groupId("org.apache.maven.plugins"),
                        artifactId("maven-release-plugin"),
                        version("2.5.3")
                ),
                goal("update-versions"),
                configuration(
                        element(name("developmentVersion"), version)
                ),
                executionEnvironment(
                        mavenProject,
                        mavenSession,
                        buildPluginManager
                )
        );
    }
}
