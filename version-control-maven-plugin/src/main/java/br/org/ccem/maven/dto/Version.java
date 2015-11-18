package br.org.ccem.maven.dto;

public class Version {
    private String release;
    private String bugs;
    private String features;

    public Version(String release, String bugs, String features) {
        this.release = release;
        this.bugs = bugs;
        this.features = features;
    }

    public void upgradeRelease(){
        int version = Integer.parseInt(release);
        version++;
        release = Integer.toString(version);
    }

    public void upgradeBug(){
        int version = Integer.parseInt(bugs);
        version++;
        bugs = Integer.toString(version);
    }

    public void upgradeFeatures(){
        int version = Integer.parseInt(features);
        version++;
        features = Integer.toString(version);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(release);
        stringBuilder.append(".");
        stringBuilder.append(bugs);
        stringBuilder.append(".");
        stringBuilder.append(features);

        return stringBuilder.toString();
    }
}
