package com.example.oompaloompashr

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.oompaloompashr.api.HRService
import com.example.oompaloompashr.data.WorkerDetailRepository
import com.example.oompaloompashr.db.HRDatabase
import com.example.oompaloompashr.data.WorkersListRepository
import com.example.oompaloompashr.ui.workerDetail.WorkerDetailViewModelFactory
import com.example.oompaloompashr.ui.workerlist.WorkersListViewModelFactory

object Injection {

    private fun provideWorkersListRepository(context: Context): WorkersListRepository {
        return WorkersListRepository(HRService.create(), HRDatabase.getInstance(context))
    }

    fun provideWorkersListViewModelFactory(context: Context, owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return WorkersListViewModelFactory(owner, provideWorkersListRepository(context))
    }


    private fun provideWorkersDetailRepository(context: Context): WorkerDetailRepository {
        return WorkerDetailRepository(HRService.create())
    }

    fun provideWorkersDetailViewModelFactory(context: Context, owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return WorkerDetailViewModelFactory(owner, provideWorkersDetailRepository(context))
    }
}