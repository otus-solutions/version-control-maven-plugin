package br.org.ccem.maven.factory;

import br.org.ccem.maven.dto.Version;

public class VersionNumberControl {

    private Version version;
    private String lastVersion;

    public VersionNumberControl(String lastVersion) {
        this.lastVersion = lastVersion;
        splitVersion(lastVersion);
    }

    private void splitVersion(String fullVersion) {
        String[] separated = fullVersion.split("-|\\.");
        version = new Version(separated[0], separated[1], separated[2]);
    }

    public void upgrade(VersionGroup versionGroup, Boolean snapshot) {
        if (VersionGroup.BUG.equals(versionGroup)) {
            version.upgradeBug();
        }

        if (VersionGroup.RELEASE.equals(versionGroup)) {
            version.upgradeRelease();
        }

        if (VersionGroup.FEATURE.equals(versionGroup)) {
            version.upgradeFeatures();
        }

        version.setSnapshot(snapshot);
    }

    public Version getVersion() {
        return version;
    }
}
