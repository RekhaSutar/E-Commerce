package rekha.com.ecommerce.data

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rekha.com.ecommerce.MainApplication
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.db.AppDatabase
import rekha.com.ecommerce.data.db.Tables
import rekha.com.ecommerce.data.entity.Product
import rekha.com.ecommerce.data.entity.Product.*
import rekha.com.ecommerce.data.entity.ProductMaster
import rekha.com.ecommerce.data.network.OnlineService

/**
 * Created by Rekha Sutar on 17,January,2020
 */
interface RepositoryImpl {
    companion object {
        private val db = AppDatabase.getInstance(MainApplication.getContext())

/*        fun getCategories(callBack: Repository.CallBack<List<Tables.Category>>?) {
            var categories: List<Tables.Category>? = null
            GlobalScope.launch {
                categories = db?.categoryDao()?.getAll()
                if (categories.isNullOrEmpty()) {
                    getDataFromServerAndStoreInDb(object : Repository.CallBack<Boolean> {
                        override fun onSuccess(response: Boolean) {
                            if (response) {
                                GlobalScope.launch {
                                    categories = db?.categoryDao()?.getAll()
                                    if (!categories.isNullOrEmpty()) {
                                        callBack?.onSuccess(categories!!)
                                    } else {
                                        callBack?.onFailure(MainApplication.getContext().getString(R.string.error_no_data_available))
                                    }
                                }
                            }
                        }

                        override fun onFailure(failureReason: String) {
                            callBack?.onFailure(failureReason)
                        }

                    })
                } else {
                    callBack?.onSuccess(categories!!)
                }

            }

        }*/
        fun getDataFromServerAndStoreInDb(callBack: Repository.CallBack<Boolean>) {

            OnlineService().getProductMaster(object : Repository.CallBack<ProductMaster> {
                override fun onSuccess(response: ProductMaster) {
                    storeData(response)
                    callBack.onSuccess(true)
                }

                override fun onFailure(failureReason: String) {
                    callBack.onFailure(failureReason)
                }

            })
        }

        private fun storeData(response: ProductMaster) {

            val categoryMap = mutableMapOf<Long, Tables.Category>()
            val childParentMap = mutableMapOf<Long, Long>()
            val productMap = mutableMapOf<Long, Tables.Product>()

            for (i in 0 until response.categories.size) {
                val cat = response.categories[i]
                val category = Tables.Category(cat.id.toLong(), cat.name, -1)

                categoryMap.put(category.id, category)

                for (j in 0 until cat.childCategories.size) {
                    childParentMap.put(cat.childCategories[j], category.id)
                }

                for (k in 0 until cat.products.size) {
                    val prod = cat.products[k]
                    val product = Tables.Product(prod.id, prod.name, prod.dateAdded, category.id, -1, -1, -1)
                    productMap[product.id] = product
                }
            }

            Log.d("MainActivity - ", categoryMap.toString())
            Log.d("MainActivity - ", childParentMap.toString())
            Log.d("MainActivity - ", productMap.toString())

            for ((key, value) in childParentMap) {
                categoryMap[key]?.parentId = value
            }

            for (i in 0 until response.rankings.size) {
                for (k in 0 until response.rankings[i].products.size) {
                    val productRanking = response.rankings[i].products[k]
                    when (response.rankings[i].ranking) {
                        Product.RANKING_MOST_VIEWED_PRODUCTS -> productMap[productRanking.id]?.viewCount =
                            productRanking.viewCount
                        Product.RANKING_MOST_ORDERED_PRODUCTS -> productMap[productRanking.id]?.orderCount =
                            productRanking.orderCount
                        Product.RANKING_MOST_SHARED_PRODUCTS -> productMap[productRanking.id]?.shares =
                            productRanking.shares
                    }
                }
            }

            Log.d("MainActivity - ", categoryMap.toString())
            Log.d("MainActivity - ", productMap.toString())

            GlobalScope.launch {
                categoryMap.forEach {
                    db?.categoryDao()?.insert(it.value)
                }

                productMap.forEach {
                    db?.productDao()?.insert(it.value)
                }
            }

        }

        fun getCategories(callBack: Repository.CallBack<List<Tables.Category>>){
            getCategories(-1, callBack)
        }

        fun getCategories(parentCategoryId: Long, callBack: Repository.CallBack<List<Tables.Category>>){
            var categories : List<Tables.Category>? = null
            GlobalScope.launch {
                categories = db?.categoryDao()?.getSubCategories(parentCategoryId)
                if (categories.isNullOrEmpty()) callBack.onFailure(MainApplication.getContext().getString(R.string.error_no_data_available))
                else callBack.onSuccess(categories!!)
            }
        }

        fun getProducts(parentCategoryId: Long, callBack: Repository.CallBack<List<Tables.Product>>){
            var products : List<Tables.Product>? = null
            GlobalScope.launch {
                products = db?.productDao()?.getProducts(parentCategoryId)
                if (products.isNullOrEmpty()) callBack.onFailure(MainApplication.getContext().getString(R.string.error_no_data_available))
                else callBack.onSuccess(products!!)
            }
        }

        fun getProducts(
            parentCategoryId: Long,
            callBack: Repository.CallBack<List<Tables.Product>>,
            rankingFilter: String
        ) {
            var products : List<Tables.Product>? = null
            GlobalScope.launch {
                when(rankingFilter){
                    RANKING_MOST_VIEWED_PRODUCTS -> products = db?.productDao()?.getProductsWithViewCountFilter(parentCategoryId)
                    RANKING_MOST_SHARED_PRODUCTS -> products = db?.productDao()?.getProductsWithSharesFilter(parentCategoryId)
                    RANKING_MOST_ORDERED_PRODUCTS -> products = db?.productDao()?.getProductsWithOrderCountFilter(parentCategoryId)
                }
                if (products.isNullOrEmpty()) callBack.onFailure(MainApplication.getContext().getString(R.string.error_no_data_available))
                else callBack.onSuccess(products!!)
            }
        }

    }
}