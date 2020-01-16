package rekha.com.ecommerce.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rekha.com.ecommerce.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by Rekha Sutar on 15,January,2020
 */
class NetworkServiceFactory {
    companion object {
        private var client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor(StethoInterceptor())
            .build()

        private lateinit var retrofit: Retrofit

        internal fun getNetworkInstance(baseUrl: String): NetworkService {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()
            System.out.println("inside retro")
            return retrofit.create(NetworkService::class.java)
        }
    }

}