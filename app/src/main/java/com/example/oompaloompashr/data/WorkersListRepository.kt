package com.example.oompaloompashr.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.oompaloompashr.api.HRService

import com.example.oompaloompashr.db.HRDatabase
import com.example.oompaloompashr.model.Result
import kotlinx.coroutines.flow.Flow


class WorkersListRepository(
    private val service: HRService,
    private val database: HRDatabase
) {
    fun getSearchResultStream(query: String): Flow<PagingData<Result>> {
        Log.d("GithubRepository", "New query: $query")

        // appending '%' so we can allow other characters to be before and after the query string
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.resultDao().reposByName(dbQuery) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = HrRemoteMediator(
                query,
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}