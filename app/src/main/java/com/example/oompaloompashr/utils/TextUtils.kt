package com.example.oompaloompashr.utils

class TextUtils {
    companion object{
        fun getHeightText(height: Int?): String{
            return buildString {
                this.append(height)
                this.append(" cm")
            }
        }
    }
}