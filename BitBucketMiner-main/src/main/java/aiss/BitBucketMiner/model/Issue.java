package aiss.BitBucketMiner.model;

import aiss.BitBucketMiner.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {
    private String id;
    private String title;
    private String state;

    // Label-related fields
    @JsonProperty("kind")
    private String kind;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("component")
    private String component;

    @JsonProperty("content")
    private Content content;

    @JsonProperty("created_on")
    private String createdAt;

    @JsonProperty("updated_on")
    private String updatedAt;

    @JsonProperty("closed_on")
    private String closedAt;

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("reporter")
    private User reporter;

    @JsonProperty("assignee")
    private User assignee;

    @JsonProperty("author")
    private User author;

    @JsonProperty("links")
    private IssueLinks links;

    public String getDescription() {
        return content != null ? content.getRaw() : null;
    }

    public List<String> getLabels() {
        List<String> labels = new java.util.ArrayList<>();
        if (kind != null && !kind.isBlank()) labels.add(kind);
        if (priority != null && !priority.isBlank()) labels.add(priority);
        if (component != null && !component.isBlank()) labels.add(component);
        return labels;
    }

    public String getWebUrl() {
        return links != null && links.getHtml() != null ? links.getHtml().getHref() : null;
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Content {
    @JsonProperty("raw")
    private String raw;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class IssueLinks {
    @JsonProperty("html")
    private Link html;
}
