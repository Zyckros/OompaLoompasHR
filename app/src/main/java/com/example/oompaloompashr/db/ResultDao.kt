package com.example.oompaloompashr.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oompaloompashr.model.Result

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Result>)

    @Query(
        "SELECT * FROM result WHERE " +
                "firstName LIKE :queryString"
    )
    fun reposByName(queryString: String): PagingSource<Int, Result>

    @Query("DELETE FROM result")
    suspend fun clearRepos()
}