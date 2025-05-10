package aiss.GithubMiner.service;

import aiss.GithubMiner.model.FullProject;
import aiss.GithubMiner.model.Comment;
import aiss.GithubMiner.model.Project;
import aiss.GithubMiner.model.User;
import aiss.GithubMiner.model.commit.Commit;
import aiss.GithubMiner.model.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FullProjectService {

    @Autowired
    RestTemplate restTemplate;

    public FullProjectService getFullProjectData(String owner, String repo) {

        Project project = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo, Project.class);
        Commit[] commits = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/commits", Commit[].class);
        Issue[] issues = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/issues", Issue[].class);
        Comment[] comments = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/comments", Comment[].class);
        User user = restTemplate.getForObject("https://api.github.com/users/" + "Nachofuertes", User.class);

        FullProject nuevoProyecto = new FullProject(project, List.of(commits), List.of(issues), List.of(comments), user);
    }

    public Project getProject(String owner, String repo) {
        return restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo, Project.class);
    }


    public Issue[] getIssues(String owner, String repo) {
        return restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/issues", Issue[].class);
    }


    public List<Issue> getIssueCompleta(String owner, String repo) {

        List<Issue> issues = Arrays.asList(getIssues(owner, repo)); // Obtiene las issues
        for (Issue issue : issues) {
            String nombreUsuario = issue.getAuthor().getUsername();
            User user = restTemplate.getForObject("https://api.github.com/users/" + nombreUsuario, User.class);
            if (user != null) {
                issue.getAuthor().setName(user.getName());
            }

        }
        return issues;
    }

    public Commits

    public List







}






    }

}
