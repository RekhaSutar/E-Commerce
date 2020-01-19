package rekha.com.ecommerce.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 17,January,2020
 */

@Dao
interface VariantsDao {

    @Query("SELECT * FROM Variants")
    fun getAll(): List<Tables.Variants>

    @Insert
    fun insertAll(vararg variant: Tables.Variants)

}