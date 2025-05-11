
package aiss.GithubMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "web_url"})

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {


    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    public String web_url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return web_url;
    }

    @JsonProperty("html_url")
    public void setWebUrl(String webUrl) {
        this.web_url = webUrl;
    }


    @Override
    public String toString() {
        return "Project {" +
                "\n  id: '" + id + "'," +
                "\n  name: '" + name + "'," +
                "\n  webUrl: '" + web_url + "'" +
                "\n}";
    }

}
