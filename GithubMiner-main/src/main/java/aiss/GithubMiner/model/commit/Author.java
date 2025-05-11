
package aiss.GithubMiner.model.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    @JsonProperty("name")
    private String author_name;
    @JsonProperty("email")
    private String author_email;
    @JsonProperty("date")
    private String author_date;



    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthor_date() {
        return author_date;
    }

    public void setAuthor_date(String author_date) {
        this.author_date = author_date;
    }

    @Override
    public String toString() {
        return "Author {" +
                "\n  author_name: '" + author_name + "'," +
                "\n  author_email: '" + author_email + "'," +
                "\n  author_date: '" + author_date + "'" +
                "\n}";
    }

}
