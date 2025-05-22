
package aiss.GithubMiner.parse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({ "id", "title", "description", "state", "created_at", "updated_at", "closed_at", "labels", "author", "assignee", "votes", "comments"})


public class IssueDTO {

    private String id;
    private String title;
    private String description;
    private String state;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private List<String> labels;
    private UserDTO author;
    private UserDTO assignee;
    private Integer votes;
    private List<CommentDTO> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return created_at;
    }
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getUpdatedAt() {
        return updated_at;
    }
    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }

    public String getClosedAt() {
        return closed_at;
    }
    @JsonProperty("closed_at")
    public void setClosedAt(String closedAt) {
        this.closed_at = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public UserDTO getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDTO assignee) {
        this.assignee = assignee;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(IssueDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null) ? "<null>" : this.state));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.created_at == null) ? "<null>" : this.created_at));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updated_at == null) ? "<null>" : this.updated_at));
        sb.append(',');
        sb.append("closedAt");
        sb.append('=');
        sb.append(((this.closed_at == null) ? "<null>" : this.closed_at));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null) ? "<null>" : this.labels));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("assignee");
        sb.append('=');
        sb.append(((this.assignee == null) ? "<null>" : this.assignee));
        sb.append(',');
        sb.append("votes");
        sb.append('=');
        sb.append(((this.votes == null) ? "<null>" : this.votes));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null) ? "<null>" : this.comments));
        sb.append(',');

        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }



}
