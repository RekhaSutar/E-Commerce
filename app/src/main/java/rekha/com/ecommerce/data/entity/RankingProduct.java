
package rekha.com.ecommerce.data.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "view_count",
    "order_count",
    "shares"
})
public class RankingProduct {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("view_count")
    private Long viewCount;
    @JsonProperty("order_count")
    private Long orderCount;
    @JsonProperty("shares")
    private Long shares;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("view_count")
    public Long getViewCount() {
        return viewCount;
    }

    @JsonProperty("view_count")
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("order_count")
    public Long getOrderCount() {
        return orderCount;
    }

    @JsonProperty("order_count")
    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    @JsonProperty("shares")
    public Long getShares() {
        return shares;
    }

    @JsonProperty("shares")
    public void setShares(Long shares) {
        this.shares = shares;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
