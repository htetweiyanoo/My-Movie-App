package net.myanmarlinks.retrofittest.local.dao

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.myanmarlinks.retrofittest.local.entity.FavouriteTodo
import net.myanmarlinks.retrofittest.local.entity.Todo

@Dao
interface TodoDao {
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todos: List<Todo>)

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavourite(favouriteTodos: List<FavouriteTodo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("SELECT * FROM movie_db")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM movie_db WHERE id = :movieId")
    fun getDetail(movieId: Int): List<Todo>
}