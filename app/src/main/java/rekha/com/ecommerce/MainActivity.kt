package rekha.com.ecommerce

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl
import rekha.com.ecommerce.data.entity.ProductMaster

class MainActivity : AppCompatActivity() , Repository.CallBack<ProductMaster>{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RepositoryImpl.getProductMaster(this)

    }


    override fun onSuccess(response: ProductMaster) {
        Log.d("MainActivity - ", response.categories[0].products[0].name)
    }

    override fun onFailure(failureReason: String) {
        Log.d("MainActivity - ", failureReason)
    }

}
