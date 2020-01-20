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
interface VariantsDao : Daos<Tables.Variants>{

    @Query("SELECT * FROM Variants")
    override fun getAll(): List<Tables.Variants>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg variant: Tables.Variants)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(data : Tables.Variants)

    @Query("SELECT * FROM Variants WHERE `Parent Id` = :productId")
    fun getVariantsDetails(productId: Long) : List<Tables.Variants>
}