package net.myanmarlinks.retrofittest.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@TypeConverters
@Entity(tableName = "movie_table")
data class Todo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String
)

