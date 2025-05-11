
package aiss.GithubMiner.model.commit;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "title", "message", "author_name", "author_email", "authored_date", "web_url" })

// value = {"commit", "sha "}
@JsonIgnoreProperties(ignoreUnknown = true )

public class Commit {

    private String id;

    private CommitContent commitContent;

    private String web_url;

    @JsonProperty("title")
    public String getTitle() {
        return "Commit " + id.substring(0, 8);
    }

    @JsonProperty("message")
    public String getMessage() {
        return commitContent.getMessage();
    }

    @JsonProperty("author_name")
    public String getAuthor_name() {
        return commitContent.getAuthor().getAuthor_name();
    }

    @JsonProperty("author_email")
    public String getAuthor_email() {
        return commitContent.getAuthor().getAuthor_email();
    }

    @JsonProperty("authored_date")
    public String getAuthor_date() {
        return commitContent.getAuthor().getAuthor_date();
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("sha")
    public void setId(String id) {
        this.id = id;
    }
    @JsonIgnore
    public CommitContent getCommit() {
        return commitContent;
    }
    @JsonProperty("commit")
    public void setCommit(CommitContent commitContent) {
        this.commitContent = commitContent;
    }

    @JsonProperty("web_url")
    public String getWeb_url() {
        return web_url;
    }
    @JsonProperty("html_url")
    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "ListCommits{\n" +
                "  id='" + id + "',\n" +
                "  commit=" + commitContent + ",\n" +
                "  web_url='" + web_url + "'\n" +
                "}";
    }

}
