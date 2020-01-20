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
interface CategoryDao : Daos<Tables.Category> {

    @Query("SELECT * FROM Category")
    override fun getAll(): List<Tables.Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg data: Tables.Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(data : Tables.Category)

    @Query("SELECT * FROM Category WHERE `Parent Id` = :parentCategoryId")
    fun getSubCategories(parentCategoryId: Long) : List<Tables.Category>

}