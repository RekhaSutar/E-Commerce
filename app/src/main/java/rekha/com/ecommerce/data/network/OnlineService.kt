package rekha.com.ecommerce.data.network

import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.entity.ProductMaster

/**
 * Created by Rekha Sutar on 16,January,2020
 */
class OnlineService : Repository {

    override fun getProductMaster(callBack: Repository.CallBack<ProductMaster>) {
        val call = NetworkServiceFactory.getNetworkInstance(Urls.PRODUCT_MASTER_URL).getProductMaster()
        call.enqueue(ApiBaseCallBack(callBack))
    }

}