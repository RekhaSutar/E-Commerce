package rekha.com.ecommerce.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rekha Sutar on 16,January,2020
 */

interface Tables {

    @Entity(tableName = "Category")
    data class Category(
        @PrimaryKey @ColumnInfo(name = "Id") var id: Long,
        @ColumnInfo(name = "Name") var name: String,
        @ColumnInfo(name = "Parent Id") var parentId: Long?
    )

    @Entity(tableName = "Product")
    data class Product(
        @PrimaryKey @ColumnInfo(name = "Id") var id: Long,
        @ColumnInfo(name = "Name") var name: String,
        @ColumnInfo(name = "Date Added") var dateAdded: String,
        @ColumnInfo(name = "Category Id") var categoryId: Long,
        @ColumnInfo(name = "View Count") var viewCount : Long,
        @ColumnInfo(name = "Order Count") var orderCount : Long,
        @ColumnInfo(name = "Shares") var shares : Long
    )

    @Entity(tableName = "Variants")
    data class Variants(
        @PrimaryKey @ColumnInfo(name = "Id") var id: Long,
        @ColumnInfo(name = "Color") var color: String,
        @ColumnInfo(name = "Size") var size: Long?,
        @ColumnInfo(name = "Price") var price: Long,
        @ColumnInfo(name = "Parent Id") var parentId: Long
    )

    @Entity(tableName = "Tax")
    data class Tax(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "Name") var name: String,
        @ColumnInfo(name = "Value") var value: Float,
        @ColumnInfo(name = "Parent Id") var parentId: Long
    )

}