// BitBucketCommitResponse.java
package aiss.BitBucketMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitBucketCommitResponse {
    private List<Commit> values;
    private String next;
    private Integer pagelen;
    private Integer size;
}