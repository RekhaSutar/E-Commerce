package rekha.com.ecommerce.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import rekha.com.ecommerce.R
import rekha.com.ecommerce.utils.BaseFunctionI

/**
 * Created by Rekha Sutar on 19,January,2020
 */
abstract class BaseActivity : AppCompatActivity(), BaseFunctionI {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
    }

    override fun setContentView(layoutResID: Int) {
        val inflater = LayoutInflater.from(this)
        inflater.inflate(layoutResID, flContainer, true)
    }
    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        runOnUiThread(Runnable {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

    }

    override fun showErrorMessage(errorMessage: String) {
        runOnUiThread(Runnable {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

}