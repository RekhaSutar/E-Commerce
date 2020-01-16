package rekha.com.ecommerce.data.network

import android.util.Log
import rekha.com.ecommerce.MainApplication
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.entity.ProductMaster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rekha Sutar on 16,January,2020
 */
class OnlineService {
    companion object val TAG = OnlineService::class.java.simpleName
    fun getProductMaster(callBack: ApiCallBack<ProductMaster>) {

        val call = NetworkServiceFactory.getNetworkInstance(Urls.PRODUCT_MASTER_URL).getProductMaster()
        call.enqueue(object : Callback<ProductMaster> {
            override fun onFailure(call: Call<ProductMaster>, t: Throwable) {
                Log.e(TAG, MainApplication.getContext().getString(R.string.error_api_failed))
                callBack.onFailure(MainApplication.getContext().getString(R.string.error_something_went_wrong))
            }

            override fun onResponse(call: Call<ProductMaster>, response: Response<ProductMaster>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null)
                    callBack.onSuccess(responseBody!!)
                else {
                    Log.e(TAG, MainApplication.getContext().getString(R.string.error_null_response))
                    callBack.onFailure(MainApplication.getContext().getString(R.string.error_something_went_wrong))
                }
            }

        })
    }


    interface ApiCallBack<T> {
        fun onSuccess(response: T)
        fun onFailure(failureReason: String)
    }
}