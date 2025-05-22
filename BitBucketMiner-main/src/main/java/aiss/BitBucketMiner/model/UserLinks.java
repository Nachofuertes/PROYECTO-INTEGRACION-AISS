package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLinks {

    @JsonProperty("avatar")
    private Link avatar;

    @JsonProperty("html")
    private Link html;
}
