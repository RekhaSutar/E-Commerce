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
interface TaxDao {

    @Query("SELECT * FROM Tax")
    fun getAll(): List<Tables.Tax>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tax: Tables.Tax)

}