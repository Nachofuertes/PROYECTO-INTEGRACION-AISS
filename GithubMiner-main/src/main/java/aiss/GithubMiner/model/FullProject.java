package aiss.GithubMiner.model;

import aiss.GithubMiner.model.commit.Commit;
import aiss.GithubMiner.model.issue.Issue;

import java.util.List;

public class FullProject {

    private Project project;
    private List<Commit> commits;
    private List<Issue> issues;
    private List<Comment> comments;
    private User users;

    public FullProject(Project project, List<Commit> commits, List<Issue> issues, List<Comment> comments, User users) {
        this.project = project;
        this.commits = commits;
        this.issues = issues;
        this.comments = comments;
        this.users = users;
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

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "FullProject{" +
                "project=" + project +
                ", commits=" + commits +
                ", issues=" + issues +
                ", comments=" + comments +
                ", users=" + users +
                '}';
    }
}
