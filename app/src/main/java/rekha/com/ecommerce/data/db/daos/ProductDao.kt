package rekha.com.ecommerce.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 17,January,2020
 */

@Dao
interface ProductDao : Daos<Tables.Product>{

    @Query("SELECT * FROM Product")
    override fun getAll(): List<Tables.Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg data: Tables.Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(data : Tables.Product)

    @Query("SELECT * FROM Product WHERE `Category Id` = :parentCategoryId")
    fun getProducts(parentCategoryId: Long) : List<Tables.Product>

    @Query("SELECT * FROM Product WHERE `Category Id` = :parentCategoryId ORDER BY Shares DESC")
    fun getProductsWithSharesFilter(parentCategoryId: Long) : List<Tables.Product>

    @Query("SELECT * FROM Product WHERE `Category Id` = :parentCategoryId ORDER BY `View Count` DESC")
    fun getProductsWithViewCountFilter(parentCategoryId: Long) : List<Tables.Product>

    @Query("SELECT * FROM Product WHERE `Category Id` = :parentCategoryId ORDER BY `Order Count` DESC")
    fun getProductsWithOrderCountFilter(parentCategoryId: Long) : List<Tables.Product>

    @Query("SELECT * FROM Product WHERE `Id` = :productId")
    fun getProductDetails(productId: Long) : Tables.Product

}