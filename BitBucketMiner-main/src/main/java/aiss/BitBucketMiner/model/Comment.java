
package aiss.BitBucketMiner.model;

import aiss.BitBucketMiner.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {


    @JsonProperty("id")
    private String id;
    @JsonProperty("content")
    private Content content;

    @JsonProperty("created_on")
    private String createdAt;
    @JsonProperty("updated_on")
    private String updatedAt;
    @JsonProperty("user")
    private User author;

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

    public String getBody() {
        return content != null ? content.getRaw() : null;
    }

    public void setBody(String body) {
        if (this.content == null) this.content = new Content();
        this.content.setRaw(body);
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
                "\n  body: '" + getBody() + "'," +
                "\n  createdAt: '" + createdAt + "'," +
                "\n  updatedAt: '" + updatedAt + "'," +
                "\n  author: " + author +
                "\n}";
    }

}
