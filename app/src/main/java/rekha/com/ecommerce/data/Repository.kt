package rekha.com.ecommerce.data

import rekha.com.ecommerce.data.entity.ProductMaster

/**
 * Created by Rekha Sutar on 17,January,2020
 */
interface Repository {
    fun getProductMaster(callBack: CallBack<ProductMaster>)


    interface CallBack<T> {
        fun onSuccess(response: T)
        fun onFailure(failureReason: String)
    }
}