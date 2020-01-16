package rekha.com.ecommerce.data.db.daos

import androidx.room.Insert
import androidx.room.Query
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 17,January,2020
 */

interface CategoryDao : Daos<Tables.Category> {

    @Query("SELECT * FROM Tables.Category")
    override fun getAll(): List<Tables.Category>

    @Insert
    override fun insertAll(vararg data: Tables.Category)

}