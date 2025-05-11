package aiss.GithubMiner.modelDTO;

import aiss.GithubMiner.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class IssueDTOWithComment {

    private IssueDTO issue;
    private List<Comment> comments;

    public IssueDTOWithComment(IssueDTO issue, List<Comment> comments) {
        this.issue = issue;
        this.comments = comments;
    }

    public IssueDTO getIssue() {
        return issue;
    }

    public void setIssue(IssueDTO issue) {
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
        return "IssueDTOWithComment {" +
                "\n  issue: " + issue + "," +
                "\n  comments: " + comments +
                "\n}";
    }

}
