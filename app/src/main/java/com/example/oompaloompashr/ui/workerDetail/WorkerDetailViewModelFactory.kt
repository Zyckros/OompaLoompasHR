package com.example.oompaloompashr.ui.workerDetail

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.oompaloompashr.data.WorkerDetailRepository
import com.example.oompaloompashr.ui.workerlists.workerDetail.WorkerDetailViewModel
import com.example.oompaloompashr.ui.workerlists.workerlist.WorkerListViewModel

class WorkerDetailViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: WorkerDetailRepository
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(WorkerDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkerDetailViewModel(handle, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}