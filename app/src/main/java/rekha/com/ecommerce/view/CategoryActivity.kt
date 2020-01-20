package rekha.com.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.*
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl
import rekha.com.ecommerce.data.db.Tables
import rekha.com.ecommerce.utils.ItemOffsetDecoration
import rekha.com.ecommerce.view.adapters.CategoryAdapter

class CategoryActivity : BaseActivity() , View.OnClickListener, Repository.CallBack<List<Tables.Category>> {

    companion object{
        const val ARGS_OPEN_SUB_CATEGORY = "args_open_sub_category"
        const val ARGS_CATEGORY_ID = "args_category_id"
    }

    private var adapter : CategoryAdapter? = null
    private var openSubCategory : Boolean = false
    private var categoryId : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        adapter = CategoryAdapter(this)
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.item_offset);
        rvList.addItemDecoration(itemDecoration)

        rvList.adapter = adapter

        showProgressBar()
        getExtraData()
        getCategories()
    }

    private fun getExtraData(){
        if (intent.extras != null){
            openSubCategory = intent.getBooleanExtra(ARGS_OPEN_SUB_CATEGORY, false)
            categoryId = intent.getLongExtra(ARGS_CATEGORY_ID, -1)
        }

    }

    private fun getCategories(){
        if (categoryId != null){
            RepositoryImpl.getCategories(categoryId!!, this)
        }else{
            RepositoryImpl.getCategories(this)
        }
    }

    override fun onSuccess(categories: List<Tables.Category>) {
        hideProgressBar()
        adapter?.setData(categories)
    }

    override fun onFailure(failureReason: String) {
        hideProgressBar()

        val intent = Intent(this@CategoryActivity, ProductActivity::class.java)
        intent.putExtra(ARGS_CATEGORY_ID, categoryId)
        startActivity(intent)

        finish()
    }

    override fun onClick(v: View) {

        categoryId = v.tag as Long
        Log.e("Cat id - ", categoryId.toString())
        val intent = Intent(this@CategoryActivity, CategoryActivity::class.java)
        intent.putExtra(ARGS_CATEGORY_ID, categoryId!!)
        startActivity(intent)

    }

}
