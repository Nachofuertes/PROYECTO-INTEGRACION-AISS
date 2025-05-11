package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("links")
    private UserLinks links;

    @JsonProperty("account_id")
    private String accountId;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserLinks {
        @JsonProperty("avatar")
        private Link avatar;
        @JsonProperty("html")
        private Link html;
    }
}