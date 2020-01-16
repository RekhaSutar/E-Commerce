package rekha.com.ecommerce

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

/**
 * Created by Rekha Sutar on 16,January,2020
 */
class MainApplication : Application() {
    companion object {
        private lateinit var instance: MainApplication

        fun getContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


        Stetho.initializeWithDefaults(this)
    }




}