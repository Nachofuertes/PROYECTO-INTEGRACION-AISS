// Project.java
package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private String id;
    private String name;

    @JsonProperty("html_url")
    private String webUrl;

    private List<Commit> commits;
    private List<Issue> issues;
}