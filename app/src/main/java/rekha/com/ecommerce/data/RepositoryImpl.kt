package rekha.com.ecommerce.data

import rekha.com.ecommerce.data.entity.ProductMaster
import rekha.com.ecommerce.data.network.OnlineService

/**
 * Created by Rekha Sutar on 17,January,2020
 */
interface RepositoryImpl {
    companion object {

        fun getProductMaster(callBack: Repository.CallBack<ProductMaster>) {
            OnlineService().getProductMaster(callBack)
        }

    }
}