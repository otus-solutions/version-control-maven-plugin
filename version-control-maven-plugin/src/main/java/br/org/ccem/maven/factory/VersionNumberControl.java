package br.org.ccem.maven.factory;

import br.org.ccem.maven.dto.Version;

public class VersionNumberControl {

    private Version version;

    public VersionNumberControl(String lastVersion) {
        splitVersion(lastVersion);
    }

    private void splitVersion(String fullVersion) {
        String[] separated = fullVersion.split("-|\\.");
        version = new Version(separated[0], separated[1], separated[2]);
    }

    public void upgrade(VersionGroup versionGroup) {
        if (VersionGroup.BUG.equals(versionGroup)) {
            version.upgradeBug();
        }

        if (VersionGroup.RELEASE.equals(versionGroup)) {
            version.upgradeRelease();
        }

        if (VersionGroup.RELEASE_CANDIDATE.equals(versionGroup)) {
            version.upgradeReleaseCandidate();
        }

        if (VersionGroup.FEATURE.equals(versionGroup)) {
            version.upgradeFeatures();
        }
    }

    public Version getVersion() {
        return version;
    }
}
