
package aiss.GithubMiner.model.commit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ListCommits {

    @JsonProperty("sha")
    private String id;

    @JsonProperty("Commit.")
    private Commit commit;

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

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        return "ListCommits{" +
                "id='" + id + '\'' +
                ", commit=" + commit +
                ", web_url='" + web_url + '\'' +
                '}';
    }
}
