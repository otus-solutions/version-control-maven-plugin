package br.org.ccem.maven.dto;

import br.org.ccem.maven.exceptions.DataNotFoundException;

import java.util.List;

public class NexusSearch {

    private List<RepositoryDetail> repoDetails;
    private List<ProjectData> data;

    public List<ProjectData> getData() throws DataNotFoundException {
        if(!data.isEmpty()){
            return data;
        }else {
            throw new DataNotFoundException();
        }
    }

    public List<RepositoryDetail> getRepoDetails() {
        return repoDetails;
    }
}
