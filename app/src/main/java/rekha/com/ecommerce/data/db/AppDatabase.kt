package rekha.com.ecommerce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rekha.com.ecommerce.data.db.daos.CategoryDao
import rekha.com.ecommerce.data.db.daos.ProductDao
import rekha.com.ecommerce.data.db.daos.TaxDao
import rekha.com.ecommerce.data.db.daos.VariantsDao

/**
 * Created by Rekha Sutar on 16,January,2020
 */

@Database(
    entities = [Tables.Category::class, Tables.Product::class, Tables.Variants::class, Tables.Tax::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object{

        private const val DATABASE_NAME = "e-commerce.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
                if (instance == null) {
                    synchronized(AppDatabase::class) {}
                    instance = Room.databaseBuilder( context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME )
                        .build()
                }
            return instance
        }

        fun destroyDataBase(){
            instance = null
        }
    }

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun variantsDao(): VariantsDao
    abstract fun taxDao(): TaxDao
}