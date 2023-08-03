package com.example.oompaloompashr.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var idFavorite: Int,
    @field:SerializedName("color")
    var color: String,
    @field:SerializedName("food")
    var food: String,
    @field:SerializedName("random_string")
    var randomString: String,
    @field:SerializedName("song")
    var song: String
)