package aiss.GithubMiner.model;

import aiss.GithubMiner.model.commit.Commit;

import java.util.List;

public class FullProject {

    private Project project;
    private List<Commit> commits;
    private List<IssueWithComment> issuesWithComment;

    public FullProject(Project project, List<Commit> commits, List<IssueWithComment> issues) {
        this.project = project;
        this.commits = commits;
        this.issuesWithComment = issuesWithComment;
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

    public List<IssueWithComment> getIssues() {
        return issuesWithComment;
    }

    public void setIssues(List<IssueWithComment> issues) {
        this.issuesWithComment = issues;
    }




    @Override
    public String toString() {
        return "FullProject {" +
                "\n  project: " + project + "," +
                "\n  commits: " + commits + "," +
                "\n  issues: " + issuesWithComment +
                "\n}";
    }

}
