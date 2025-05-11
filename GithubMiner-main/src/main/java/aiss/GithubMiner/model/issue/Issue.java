
package aiss.GithubMiner.model.issue;

import aiss.GithubMiner.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({ "id", "title", "description", "state", "created_at", "updated_at", "closed_at", "labels", "author", "asignee", "votes", })

@JsonIgnoreProperties(ignoreUnknown = true)

public class Issue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;

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
    private List<Label> labels = new ArrayList<Label>();

    private Reaction reactions;

    public static class Reaction {
        @JsonIgnoreProperties(ignoreUnknown = true)

        private Integer votes;

        @JsonProperty("votes")
        public Integer getVotes() {
            return votes;
        }
        @JsonProperty("total_count")
        public void setVotes(Integer votes) {
            this.votes = votes;
        }


        @Override
        public String toString() {
            return "Reaction {" +
                    "\n  votes: " + votes +
                    "\n}";
        }

    }
    @JsonIgnore
    public Reaction getReactions() {
        return reactions;
    }
    @JsonProperty("total_count")
    public void setReactions(Reaction reactions) {
        this.reactions = reactions;
    }

    @JsonProperty("votes")
    public Integer getVotes() {
        return reactions.getVotes();
    }



    @JsonProperty("user")
    private User author;
    @JsonProperty("assignee")
    private User assignee; // Usuario asignado (si hay solo uno)


    ///

    //Este número pertenece a una issue y es el que me va a permitir sacar los comments de una issue
    //Haciendo https://api.github.com/repos/torvalds/linux/issues/{numero}/comments

    private Integer number;

    ///


    @JsonIgnore
    public Integer getNumber() {

        return number;
    }
    @JsonProperty("number")
    public void setNumber(Integer number) {

        this.number = number;
    }

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

    @JsonProperty("description")
    public String getDescription() {

        return description;
    }
    @JsonProperty("body")
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
        return labels.stream()
                .map(Label::getName)
                .collect(Collectors.toList());
    }

    public void setLabels(List<Label> labels) {
        labels.stream()
                .map(Label::getName)
                .collect(Collectors.toList());


    }
    /*
    @JsonProperty("label")
    public List<String> getLabelNames(){
        return labels.stream()
                .map(Label::getName)
                .collect(Collectors.toList());
    }

     */


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
                "\n  number: " + number +
                "\n}";
    }

}
