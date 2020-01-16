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
        @PrimaryKey @ColumnInfo(name = "Id") var id: Int,
        @ColumnInfo(name = "Name") var name: String,
        @ColumnInfo(name = "Parent Id") var parentId: Int
    )

    @Entity(tableName = "Product")
    data class Product(
        @PrimaryKey @ColumnInfo(name = "Id") var id: Int,
        @ColumnInfo(name = "Name") var name: String,
        @ColumnInfo(name = "Date Added") var dateAdded: String
    )

    @Entity(tableName = "Variants")
    data class Variants(
        @PrimaryKey @ColumnInfo(name = "Id") var id: Int,
        @ColumnInfo(name = "Color") var color: String,
        @ColumnInfo(name = "Size") var size: String,
        @ColumnInfo(name = "Price") var price: String
    )

    @Entity(tableName = "Tax")
    data class Tax(
        @ColumnInfo(name = "Name") var name: Int,
        @ColumnInfo(name = "Value") var value: String
    )

}