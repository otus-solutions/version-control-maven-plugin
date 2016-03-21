package br.org.ccem.maven.dto;

import java.io.IOException;

public class GitNotes {
    private String pullCommand = "git pull -f";
    private String pushCommand = "git push";
    private String fetchCommand = "git fetch";
    private String noteCommand = "git notes --ref=commit-type add -m";
    private String noteRefs = "refs/notes/commit-type:refs/notes/commit-type";

    public Process fetchNotes(Runtime runtime, String remote) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(fetchCommand);
        stringBuilder.append(" ");
        stringBuilder.append(remote);
        stringBuilder.append(" ");
        stringBuilder.append(noteRefs);

        return runtime.exec(stringBuilder.toString());
    }

    public Process createNote(Runtime runtime, String commitType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(noteCommand);
        stringBuilder.append("'{"+commitType+"}'");
        stringBuilder.append(" ");
        stringBuilder.append("HEAD");

        return runtime.exec(stringBuilder.toString());
    }

    public Process pushNote(Runtime runtime, String remote) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(pushCommand);
        stringBuilder.append(" ");
        stringBuilder.append(remote);
        stringBuilder.append(" ");
        stringBuilder.append(noteRefs);

        return runtime.exec(stringBuilder.toString());
    }

}
