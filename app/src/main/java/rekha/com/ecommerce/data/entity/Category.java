
package rekha.com.ecommerce.data.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "products",
    "child_categories"
})
public class Category {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("products")
    private List<Product> products = null;
    @JsonProperty("child_categories")
    private List<Long> childCategories = null;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonProperty("child_categories")
    public List<Long> getChildCategories() {
        return childCategories;
    }

    @JsonProperty("child_categories")
    public void setChildCategories(List<Long> childCategories) {
        this.childCategories = childCategories;
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
