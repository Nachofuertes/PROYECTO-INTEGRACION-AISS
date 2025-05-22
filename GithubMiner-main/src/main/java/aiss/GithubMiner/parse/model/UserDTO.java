
package aiss.GithubMiner.parse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "username", "name", "avatar_url", "web_url"})

public class    UserDTO {


    private String id;
    private String username;
    private String name;
    private String avatar_url;
    private String web_url;

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
        return avatar_url;
    }
    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }

    public String getWebUrl() {
        return web_url;
    }
    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.web_url = webUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("avatarUrl");
        sb.append('=');
        sb.append(((this.avatar_url == null)?"<null>":this.avatar_url));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.web_url == null)?"<null>":this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
