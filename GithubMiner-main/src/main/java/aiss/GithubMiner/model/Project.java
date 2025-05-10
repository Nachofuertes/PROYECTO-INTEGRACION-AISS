
package aiss.GithubMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {


    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("html_url")
    public String web_Url;

    public Project(String id, String name, String webUrl) {
        this.id = id;
        this.name = name;
        this.web_Url = webUrl;
    }

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

    public String getWebUrl() {
        return web_Url;
    }

    public void setWebUrl(String webUrl) {
        this.web_Url = webUrl;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", webUrl='" + web_Url + '\'' +
                '}';
    }
}
