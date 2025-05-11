package aiss.GithubMiner.modelDTO;

import aiss.GithubMiner.model.IssueWithComment;
import aiss.GithubMiner.model.Project;
import aiss.GithubMiner.model.commit.Commit;

import java.util.List;

public class FullProjectDTO {

    private Project project;
    private List<Commit> commits;
    private List<IssueDTOWithComment> issues;

    public FullProjectDTO(Project project, List<Commit> commits, List<IssueDTOWithComment> issues) {
        this.project = project;
        this.commits = commits;
        this.issues = issues;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<IssueDTOWithComment> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDTOWithComment> issues) {
        this.issues = issues;
    }




    @Override
    public String toString() {
        return "FullProject {" +
                "\n  project: " + project + "," +
                "\n  commits: " + commits + "," +
                "\n  issues: " + issues +
                "\n}";
    }

}
