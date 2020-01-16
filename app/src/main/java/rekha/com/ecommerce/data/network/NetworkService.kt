package rekha.com.ecommerce.data.network

import rekha.com.ecommerce.data.entity.ProductMaster
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Rekha Sutar on 15,January,2020
 */
interface NetworkService {

    @GET("json")
    fun getProductMaster() : Call<ProductMaster>

}