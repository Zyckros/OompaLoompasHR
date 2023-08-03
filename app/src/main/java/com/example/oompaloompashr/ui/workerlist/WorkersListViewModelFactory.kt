package com.example.oompaloompashr.ui.workerlist

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.oompaloompashr.data.WorkersListRepository
import com.example.oompaloompashr.ui.workerlists.workerlist.WorkerListViewModel

class WorkersListViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: WorkersListRepository
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(WorkerListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkerListViewModel(handle, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}