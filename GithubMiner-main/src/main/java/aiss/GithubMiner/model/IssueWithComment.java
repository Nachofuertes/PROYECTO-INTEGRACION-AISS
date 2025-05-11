package aiss.GithubMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class IssueWithComment {

    private Issue issue;
    private List<Comment> comments;

    public IssueWithComment(Issue issue, List<Comment> comments) {
        this.issue = issue;
        this.comments = comments;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "IssueWithComment {" +
                "\n  issue: " + issue + "," +
                "\n  comments: " + comments +
                "\n}";
    }

}
