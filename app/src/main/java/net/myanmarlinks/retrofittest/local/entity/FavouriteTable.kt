package net.myanmarlinks.retrofittest.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@TypeConverters
@Entity(tableName = "favourite_table")
data class FavouriteTodo(
    @PrimaryKey
    @ColumnInfo(name = "favourite_id")
    val favouriteID: Int
)