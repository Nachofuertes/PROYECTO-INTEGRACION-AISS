
package aiss.GithubMiner.model;

import aiss.GithubMiner.parse.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {


    @JsonProperty("id")
    private String id;
    @JsonProperty("body")
    private String body;

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("user")
    private UserDTO author;

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    @Override
    public String toString() {
        return "Comment {" +
                "\n  id: '" + id + "'," +
                "\n  body: '" + body + "'," +
                "\n  createdAt: '" + createdAt + "'," +
                "\n  updatedAt: '" + updatedAt + "'," +
                "\n  author: " + author +
                "\n}";
    }

}
