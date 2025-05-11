
package aiss.GithubMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String username;
    @JsonProperty("name")
    private String name = "anonymous";
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("html_url")
    private String webUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }



    @Override
    public String toString() {
        return "User {" +
                "\n  id: '" + id + "'," +
                "\n  username: '" + username + "'," +
                "\n  name: '" + name + "'," +
                "\n  avatarUrl: '" + avatarUrl + "'," +
                "\n  webUrl: '" + webUrl + "'" +
                "\n}";
    }

}
