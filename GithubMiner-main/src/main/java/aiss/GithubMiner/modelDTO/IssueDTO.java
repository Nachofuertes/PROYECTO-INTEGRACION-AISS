
package aiss.GithubMiner.modelDTO;

import aiss.GithubMiner.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class IssueDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String description;
    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("reactions")

    private Reaction reactions;

    public static class Reaction {
        @JsonIgnoreProperties(ignoreUnknown = true)

        @JsonProperty("total_count")
        private Integer votes;


        public Integer getVotes() {
            return votes;
        }

        public void setVotes(Integer votes) {
            this.votes = votes;
        }

    }

    @JsonProperty("user")
    private User author;
    @JsonProperty("assignee")
    private User assignee; // Usuario asignado (si hay solo uno)

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

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
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Reaction getReactions() {
        return reactions;
    }

    public void setReactions(Reaction reactions) {
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Issue {" +
                "\n  id: '" + id + "'," +
                "\n  title: '" + title + "'," +
                "\n  description: '" + description + "'," +
                "\n  state: '" + state + "'," +
                "\n  createdAt: '" + createdAt + "'," +
                "\n  updatedAt: '" + updatedAt + "'," +
                "\n  closedAt: '" + closedAt + "'," +
                "\n  labels: " + labels + "," +
                "\n  reactions: " + reactions + "," +
                "\n  author: " + author + "," +
                "\n  assignee: " + assignee + "," +
                "\n}";
    }

}
