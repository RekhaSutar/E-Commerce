package rekha.com.ecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl
import rekha.com.ecommerce.data.db.Tables

class MainActivity : AppCompatActivity() , Repository.CallBack<List<Tables.Category>>{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RepositoryImpl.getCategories(this)
    }

    override fun onSuccess(categories: List<Tables.Category>) {

        Log.d("MainActivity - cats", categories.size.toString())
    }

    override fun onFailure(failureReason: String) {
        Log.d("MainActivity - ", failureReason)
    }

}
