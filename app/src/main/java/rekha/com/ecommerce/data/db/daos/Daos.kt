package rekha.com.ecommerce.data.db.daos

/**
 * Created by Rekha Sutar on 16,January,2020
 */

interface Daos<T> {
    fun getAll(): List<T>
    fun insertAll(vararg data: T)
    fun insert(data: T)
}