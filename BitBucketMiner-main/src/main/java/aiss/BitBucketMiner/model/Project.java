package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("uuid")
    private String id;

    private String name;

    @JsonProperty("full_name")
    private String fullName; // puede ser útil para trazabilidad

    @JsonProperty("links")
    private ProjectLinks links;

    private List<Commit> commits;
    private List<Issue> issues;
    private List<IssueWithComment> issueWithComments;

    public String getWebUrl() {
        return links != null && links.getHtml() != null ? links.getHtml().getHref() : null;
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ProjectLinks {
    @JsonProperty("html")
    private Link html;
}
