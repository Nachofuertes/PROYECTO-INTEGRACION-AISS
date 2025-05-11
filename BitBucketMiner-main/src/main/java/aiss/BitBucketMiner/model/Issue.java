package aiss.BitBucketMiner.model;

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

    @JsonProperty("content")
    private Content content;

    @JsonProperty("created_on")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    private LocalDateTime createdAt;

    @JsonProperty("updated_on")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    private LocalDateTime updatedAt;

    @JsonProperty("closed_on")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
    private LocalDateTime closedAt;

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
        return List.of(); // Bitbucket no incluye etiquetas por defecto
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
