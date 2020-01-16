
package rekha.com.ecommerce.data.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "view_count",
    "order_count",
    "shares"
})
public class RankingProduct {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("view_count")
    private Integer viewCount;
    @JsonProperty("order_count")
    private Integer orderCount;
    @JsonProperty("shares")
    private Integer shares;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    @JsonProperty("view_count")
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @JsonProperty("order_count")
    public Integer getOrderCount() {
        return orderCount;
    }

    @JsonProperty("order_count")
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    @JsonProperty("shares")
    public Integer getShares() {
        return shares;
    }

    @JsonProperty("shares")
    public void setShares(Integer shares) {
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
