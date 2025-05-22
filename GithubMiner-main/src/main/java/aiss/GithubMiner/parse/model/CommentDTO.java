
package aiss.GithubMiner.parse.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "body", "author","created_at", "updated_at", "author" })

public class CommentDTO {



    private String id;
    private String body;
    private UserDTO author;
    private String created_at;
    private String updated_at;

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

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommentDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null) ? "<null>" : this.body));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.created_at == null) ? "<null>" : this.created_at));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updated_at == null) ? "<null>" : this.updated_at));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
