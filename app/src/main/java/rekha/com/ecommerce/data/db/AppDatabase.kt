package rekha.com.ecommerce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Rekha Sutar on 16,January,2020
 */

@Database(
    entities = [Tables.Category::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object{

        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "e-commerce.db")
            .build()
    }
}