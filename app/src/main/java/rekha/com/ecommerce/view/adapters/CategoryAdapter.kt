package rekha.com.ecommerce.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_text.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rekha.com.ecommerce.MainApplication
import rekha.com.ecommerce.R
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 19,January,2020
 */
class CategoryAdapter(
    private val listener: View.OnClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList: List<Tables.Category>? = null

    fun setData(list: List<Tables.Category>){
        categoryList = list
        GlobalScope.launch(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            inflater.inflate(
                R.layout.row_text,
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return if (categoryList.isNullOrEmpty()) 0
        else categoryList!!.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categoryList?.get(position)?.let { holder.bindItems(it) }
    }

    class CategoryViewHolder(itemView: View, val listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(category: Tables.Category) {
            itemView.tvName.text = category.name
            itemView.tvName.tag = category.id
            itemView.tvName.setOnClickListener(listener)
        }
    }

}