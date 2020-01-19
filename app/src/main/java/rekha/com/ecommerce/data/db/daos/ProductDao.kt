package rekha.com.ecommerce.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import rekha.com.ecommerce.data.db.Tables

/**
 * Created by Rekha Sutar on 17,January,2020
 */

@Dao
interface ProductDao : Daos<Tables.Product>{

    @Query("SELECT * FROM Product")
    override fun getAll(): List<Tables.Product>

    @Insert
    override fun insertAll(vararg data: Tables.Product)

    @Insert
    override fun insert(data : Tables.Product)

}