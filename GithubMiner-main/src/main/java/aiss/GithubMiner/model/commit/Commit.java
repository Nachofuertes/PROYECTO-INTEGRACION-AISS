
package aiss.GithubMiner.model.commit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Commit {

    @JsonProperty("sha")
    private String id;

    @JsonProperty("Commit")
    private CommitContent commitContent;

    @JsonProperty("html_url")
    private String web_url;

    public String getTitle() {
        return "Commit " + id.substring(0, 8);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommitContent getCommit() {
        return commitContent;
    }

    public void setCommit(CommitContent commitContent) {
        this.commitContent = commitContent;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "ListCommits {" +
                "\n  id: '" + id + "'," +
                "\n  title: '" + getTitle() + "'," +
                "\n  commit: " + commitContent + "," +
                "\n  web_url: '" + web_url + "'" +
                "\n}";
    }

}
