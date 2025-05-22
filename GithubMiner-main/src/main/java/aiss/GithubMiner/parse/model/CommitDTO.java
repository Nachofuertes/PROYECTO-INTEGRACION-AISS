package aiss.GithubMiner.parse.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "title", "message", "author_name", "author_email", "authored_date", "web_url" })


public class CommitDTO {

    private String id;
    private String title;
    private String message;
    private String author_name;
    private String author_email;
    private String authored_date;
    private String web_url;


    public CommitDTO(String id, String title, String message, String author_name, String author_email, String authored_date, String web_url) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.author_name = author_name;
        this.author_email = author_email;
        this.authored_date = authored_date;
        this.web_url = web_url;
    }

    public CommitDTO() {
        this.id = id;
        this.title = title;
        this.message = message;
        this.author_name = author_name;
        this.author_email = author_email;
        this.authored_date = authored_date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return author_name;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.author_name = authorName;
    }

    public String getAuthorEmail() {
        return author_email;
    }

    @JsonProperty("author_email")
    public void setAuthorEmail(String authorEmail) {
        this.author_email = authorEmail;
    }

    public String getAuthoredDate() {
        return authored_date;
    }
    @JsonProperty("authored_date")
    public void setAuthoredDate(String authoredDate) {
        this.authored_date = authoredDate;
    }

    public String getWebUrl() {
        return web_url;
    }
    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.web_url = webUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommitDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        sb.append("authorName");
        sb.append('=');
        sb.append(((this.author_name == null) ? "<null>" : this.author_name));
        sb.append(',');
        sb.append("authorEmail");
        sb.append('=');
        sb.append(((this.author_email == null) ? "<null>" : this.author_email));
        sb.append(',');
        sb.append("authoredDate");
        sb.append('=');
        sb.append(((this.authored_date == null) ? "<null>" : this.authored_date));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.web_url == null) ? "<null>" : this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
