package com.example.oompaloompashr.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "result")
data class Result(
    @PrimaryKey @field:SerializedName("id")
    var idResult: Long,
    @field:SerializedName("age")
    var age: Int,
    @field:SerializedName("country")
    var country: String,
    @field:SerializedName("email")
    var email: String,
    @Embedded
    var favorite: Favorite,
    @field:SerializedName("first_name")
    var firstName: String,
    @field:SerializedName("gender")
    var gender: String,
    @field:SerializedName("height")
    var height: Int,
    @field:SerializedName("image")
    var image: String,
    @field:SerializedName("last_name")
    var lastName: String,
    @field:SerializedName("profession")
    var profession: String
)