package rekha.com.ecommerce.data.network

import android.util.Log
import rekha.com.ecommerce.MainApplication
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rekha Sutar on 17,January,2020
 */
class ApiBaseCallBack<T>(private val callback : Repository.CallBack<T>) : Callback<T> {

    companion object val TAG = OnlineService::class.java.simpleName

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e(TAG, MainApplication.getContext().getString(R.string.error_api_failed))
        callback.onFailure(MainApplication.getContext().getString(R.string.error_something_went_wrong))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null)
            callback.onSuccess(responseBody)
        else {
            Log.e(TAG, MainApplication.getContext().getString(R.string.error_null_response))
            callback.onFailure(MainApplication.getContext().getString(R.string.error_something_went_wrong))
        }
    }
}