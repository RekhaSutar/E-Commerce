
package rekha.com.ecommerce.data.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ranking",
    "products"
})
public class Ranking {

    @JsonProperty("ranking")
    private String ranking;
    @JsonProperty("products")
    private List<RankingProduct> products = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ranking")
    public String getRanking() {
        return ranking;
    }

    @JsonProperty("ranking")
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @JsonProperty("products")
    public List<RankingProduct> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<RankingProduct> products) {
        this.products = products;
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
