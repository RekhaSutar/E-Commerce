package rekha.com.ecommerce.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_product.view.*
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 19,January,2020
 */
class VariantsAdapter(private val isSizeVariant :Boolean,
    private val listener: View.OnClickListener
) : RecyclerView.Adapter<VariantsAdapter.VariantsViewHolder>() {

    private var list: List<Tables.Variants>? = null

    fun setData(list: List<Tables.Variants>){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VariantsViewHolder(
            inflater.inflate(
                R.layout.row_product,
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0
        else list!!.size
    }

    override fun onBindViewHolder(holder: VariantsViewHolder, position: Int) {
        list?.get(position)?.let { holder.bindItems(it, isSizeVariant) }
    }

    class VariantsViewHolder(itemView: View, val listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(variant: Tables.Variants, isSizeVariant: Boolean) {
            if (isSizeVariant){
                itemView.tvVariantName.text = variant.size.toString()
            }else{
                itemView.tvVariantName.text = variant.color
            }
            itemView.tvVariantName.tag = variant
            itemView.tvVariantName.setTag(R.string.tag, isSizeVariant)
            itemView.tvVariantName.setOnClickListener(listener)
        }
    }

}