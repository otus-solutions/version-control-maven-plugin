package br.org.ccem.maven.dto;

public class ProjectData {

    private String groupId;
    private String artifactId;
    private String version;
    private String latestSnapshot;
    private String latestSnapshotRepositoryId;

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getLatestSnapshot() {
        return latestSnapshot;
    }

    public String getLatestSnapshotRepositoryId() {
        return latestSnapshotRepositoryId;
    }
}
