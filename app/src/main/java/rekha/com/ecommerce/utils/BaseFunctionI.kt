package rekha.com.ecommerce.utils

/**
 * Created by Rekha Sutar on 19,January,2020
 */
interface BaseFunctionI {
    fun showMessage(message : String)
    fun showErrorMessage(errorMessage : String)

    fun showProgressBar()
    fun hideProgressBar()
}