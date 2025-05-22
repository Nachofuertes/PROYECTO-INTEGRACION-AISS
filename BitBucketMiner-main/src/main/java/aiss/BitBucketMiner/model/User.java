package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("account_id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("links")
    private UserLinks links;

    // Métodos personalizados

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return links != null && links.getAvatar() != null ? links.getAvatar().getHref() : null;
    }

    public String getWebUrl() {
        return links != null && links.getHtml() != null ? links.getHtml().getHref() : null;
    }
}
