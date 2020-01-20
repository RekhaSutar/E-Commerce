package rekha.com.ecommerce.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_text.view.*
import kotlinx.coroutines.GlobalScope
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 19,January,2020
 */
class ProductAdapter(
    private val listener: View.OnClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Tables.Product>? = null

    fun setData(list: List<Tables.Product>){
        productList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(
            inflater.inflate(
                R.layout.row_text,
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return if (productList.isNullOrEmpty()) 0
        else productList!!.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        productList?.get(position)?.let { holder.bindItems(it) }
    }

    class ProductViewHolder(itemView: View, val listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(product: Tables.Product) {
            itemView.tvName.text = product.name
            itemView.tvName.tag = product.id
            itemView.tvName.setOnClickListener(listener)
        }
    }

}