package aiss.GithubMiner.service;

import aiss.GithubMiner.model.Project;
import aiss.GithubMiner.model.commit.ListCommits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FullProjectService {

    @Autowired
    RestTemplate restTemplate;

    public FullProjectService getFullProjectData(String owner, String repo){

            Project project = restTemplate.getForObject("https://api.github.com/repos/"+owner + "/"+repo, Project.class);
            ListCommits commits = restTemplate.getForObject("https://api.github.com/repos/"+owner + "/"+repo+"/commits", ListCommits.class);



    }

}
