package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    private String hash;

    @JsonProperty("date")
    private String authoredDate;

    @JsonProperty("message")
    private String message;

    @JsonProperty("links")
    private CommitLinks links;

    @JsonProperty("author")
    private CommitAuthor author;

    // Getters adicionales para mantener compatibilidad
    public String getId() {
        return hash;
    }

    public String getTitle() {
        return message != null ? message.split("\n")[0] : "";
    }

    public String getWebUrl() {
        return links != null && links.getHtml() != null ? links.getHtml().getHref() : null;
    }

    public String getAuthorName() {
        return author != null ? author.getUser() != null ? author.getUser().getDisplayName() : author.getRaw() : null;
    }

    public String getAuthorEmail() {
        return author != null ? author.getUser() != null ? author.getUser().getEmail() : null : null;
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class CommitLinks {
    @JsonProperty("html")
    private Link html;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Link {
    private String href;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class CommitAuthor {
    @JsonProperty("raw")
    private String raw;
    @JsonProperty("user")
    private User user;
}