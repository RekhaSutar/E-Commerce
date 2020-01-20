package rekha.com.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl

class SplashActivity : BaseActivity() , Repository.CallBack<Boolean>{

    companion object {
        private const val SPLASH_TIME_OUT:Long=1000 // 1 sec
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        showProgressBar()
        RepositoryImpl.getDataFromServerAndStoreInDb(this)
    }

    override fun onSuccess(isDataFetched: Boolean) {
        if (isDataFetched){
            Handler().postDelayed({
                hideProgressBar()
                startActivity(Intent(this, CategoryActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        }else{
            showErrorMessage(getString(R.string.error_something_went_wrong))
        }
    }

    override fun onFailure(failureReason: String) {
        hideProgressBar()
        showErrorMessage(failureReason + " - " + getString(R.string.error_something_went_wrong))
        finish()
    }

}
