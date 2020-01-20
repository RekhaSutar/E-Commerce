package rekha.com.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl
import rekha.com.ecommerce.data.db.Tables
import rekha.com.ecommerce.data.entity.Product
import rekha.com.ecommerce.utils.ItemOffsetDecoration
import rekha.com.ecommerce.view.ProductDetailsActivity.Companion.ARGS_PRODUCT_ID
import rekha.com.ecommerce.view.adapters.ProductAdapter


class ProductActivity : BaseActivity() , Repository.CallBack<List<Tables.Product>>, View.OnClickListener{

    private var adapter : ProductAdapter? = null
    private var categoryId : Long? = null
    private val rankingFilterList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        showProgressBar()
        getExtraData()

        adapter = ProductAdapter(this)
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val itemDecoration = ItemOffsetDecoration(this, R.dimen.item_offset);
        rvList.addItemDecoration(itemDecoration)

//        rvList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvList.adapter = adapter


        getProducts()
        setUpRankingFilter()
    }

    private fun getExtraData(){
        if (intent.extras != null){
            categoryId = intent.getLongExtra(CategoryActivity.ARGS_CATEGORY_ID, -1)
        }
    }

    private fun getProducts(){
        if (categoryId != null){
            RepositoryImpl.getProducts(categoryId!!, this)
        }else{
            showErrorMessage(getString(R.string.error_no_data_available))
        }
    }

    private fun getProducts(rankingFilter: String) {
        if (categoryId != null && !TextUtils.isEmpty(rankingFilter)){
            RepositoryImpl.getProducts(categoryId!!, this, rankingFilter)
        }else{
            showErrorMessage(getString(R.string.error_no_data_available))
        }
    }

    override fun onSuccess(products: List<Tables.Product>) {
        hideProgressBar()
        GlobalScope.launch(Dispatchers.Main) {
            adapter?.setData(products)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onFailure(failureReason: String) {
        hideProgressBar()
        showErrorMessage(failureReason)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this@ProductActivity, ProductDetailsActivity::class.java)
        intent.putExtra(ARGS_PRODUCT_ID, v?.tag as Long)
        startActivity(intent)
    }

    private fun setUpRankingFilter() {

        rankingFilterList.add(getString(R.string.lable_all))
        rankingFilterList.add(Product.RANKING_MOST_ORDERED_PRODUCTS)
        rankingFilterList.add(Product.RANKING_MOST_SHARED_PRODUCTS)
        rankingFilterList.add(Product.RANKING_MOST_VIEWED_PRODUCTS)

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, rankingFilterList)
        spRanking.adapter = adapter

        spRanking.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                when(rankingFilterList.get(position)){
                    Product.RANKING_MOST_ORDERED_PRODUCTS,
                    Product.RANKING_MOST_SHARED_PRODUCTS,
                    Product.RANKING_MOST_VIEWED_PRODUCTS -> getProducts(rankingFilterList.get(position))
                    else -> getProducts()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

}
