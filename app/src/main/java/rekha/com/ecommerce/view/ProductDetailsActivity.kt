package rekha.com.ecommerce.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.Repository
import rekha.com.ecommerce.data.RepositoryImpl
import rekha.com.ecommerce.data.db.Tables
import rekha.com.ecommerce.utils.ItemOffsetDecoration
import rekha.com.ecommerce.view.adapters.VariantsAdapter

class ProductDetailsActivity : BaseActivity(), View.OnClickListener {

    companion object {
        const val ARGS_PRODUCT_ID = "args_product_id"
    }

    private var productId: Long? = null

    private var sizeAdapter: VariantsAdapter? = null
    private var colorAdapter: VariantsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setContentView(R.layout.activity_product_details)

        if (intent.extras != null) {
            productId = intent.getLongExtra(ARGS_PRODUCT_ID, -1)
        } else {
            throw IllegalStateException("productId missing argument")
        }

        getProductData()
        getTaxData()
        getVariantData()

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.item_offset)

        sizeAdapter = VariantsAdapter(true, this)
        rvSizeVariants.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvSizeVariants.addItemDecoration(itemDecoration)
        rvSizeVariants.adapter = sizeAdapter


        colorAdapter = VariantsAdapter(false, this)
        rvColorVariants.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvColorVariants.addItemDecoration(itemDecoration)
        rvColorVariants.adapter = colorAdapter
    }

    private fun getProductData() {
        RepositoryImpl.getProductDetails(productId!!, object : Repository.CallBack<Tables.Product> {
            override fun onSuccess(product: Tables.Product) {
                tvProductName.text = product.name
            }

            override fun onFailure(failureReason: String) {
                showErrorMessage(failureReason)
            }

        })
    }

    private fun getTaxData() {
        RepositoryImpl.getProductTaxData(productId!!, object : Repository.CallBack<Tables.Tax> {
            override fun onSuccess(tax: Tables.Tax) {
                tvTax.text = tax.name
                tvTaxValue.text = tax.value.toString()
            }

            override fun onFailure(failureReason: String) {
                showErrorMessage(failureReason)
            }

        })
    }

    private fun getVariantData() {
        RepositoryImpl.getProductVariants(productId!!, object : Repository.CallBack<List<Tables.Variants>> {
            override fun onSuccess(variantsList: List<Tables.Variants>) {
                processVariants(variantsList)
            }

            override fun onFailure(failureReason: String) {
                showErrorMessage(failureReason)
            }

        })
    }

    val sizeMap = mutableMapOf<Long, MutableList<Tables.Variants>>()
    val sizeVariantList = mutableListOf<Tables.Variants>()
    var isSizeVariant = false

    private fun processVariants(variantsList: List<Tables.Variants>) {
        for (i in 0 until variantsList.size) {
            val variant = variantsList[i]
            if (variant.size != null) {
                isSizeVariant = true
                if (sizeMap.contains(variant.size)) {
                    val list = sizeMap.get(variant.size!!)
                    list!!.add(variant)
                    sizeMap.put(variant.size!!, list)
                } else {
                    val list = mutableListOf<Tables.Variants>()
                    list.add(variant)
                    sizeMap.put(variant.size!!, list)
                    sizeVariantList.add(variant)
                }
            }else{
                isSizeVariant = false
                break
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val variantToDisplayOnLoad : Tables.Variants
            if (isSizeVariant){
                rvSizeVariants.visibility = View.VISIBLE
                sizeAdapter?.setData(sizeVariantList)
                sizeAdapter?.notifyDataSetChanged()

                colorAdapter?.setData(sizeMap[sizeVariantList[0].size]!!.toList())
                colorAdapter?.notifyDataSetChanged()

                variantToDisplayOnLoad = sizeMap[sizeVariantList[0].size]!![0]
            }else {
                rvSizeVariants.visibility = View.GONE

                colorAdapter?.setData(variantsList)
                colorAdapter?.notifyDataSetChanged()

                variantToDisplayOnLoad = variantsList[0]
            }

            displayProductData(variantToDisplayOnLoad)
        }

        Log.e("Blah - ", sizeMap.toString())
    }

    private fun displayProductData(variant: Tables.Variants) {
        tvColorValue.text = variant.color
        if (variant.size != null) {
            tvSizeValue.text = variant.size.toString()
        }
        tvPriceValue.text = variant.price.toString()
    }

    override fun onClick(v: View) {
        val selectedVariant = v.tag as Tables.Variants
        val isSizeSelected = v.getTag(R.string.tag) as Boolean
        if (isSizeSelected){
            colorAdapter?.setData(sizeMap[selectedVariant.size]!!.toList())
            colorAdapter?.notifyDataSetChanged()
        }
        displayProductData(selectedVariant)
    }

}
