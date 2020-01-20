package rekha.com.ecommerce.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rekha.com.ecommerce.data.db.Tables
import rekha.com.ecommerce.data.db.Tables.Tax

/**
 * Created by Rekha Sutar on 17,January,2020
 */

@Dao
interface TaxDao : Daos<Tables.Tax> {

    @Query("SELECT * FROM Tax")
    override fun getAll(): List<Tax>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    override fun insertAll(vararg tax: Tax)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(data : Tables.Tax)

    @Query("SELECT * FROM Tax WHERE `Parent Id` = :productId")
    fun getTaxDetails(productId: Long) : Tables.Tax
}