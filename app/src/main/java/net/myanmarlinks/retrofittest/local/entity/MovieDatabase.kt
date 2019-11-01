package net.myanmarlinks.retrofittest.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.myanmarlinks.retrofittest.local.entity.dao.TodoDao

@Database(
    entities = [Todo::class],
    version = 1
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun toDoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatase(context).also {
                    instance = it
                }
            }

        private fun buildDatase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java, "movie_db.sqlite"
            ).build()
    }
}