package com.example.oompaloompashr.ui.workerlists.workerDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oompaloompashr.data.WorkerDetailRepository
import com.example.oompaloompashr.model.OompaLoompaDetail
import kotlinx.coroutines.launch

class WorkerDetailViewModel(private val savedStateHandle: SavedStateHandle,
                            val workerDetailRepository: WorkerDetailRepository
) : ViewModel() {

    val worker: LiveData<OompaLoompaDetail>
        get() = _worker

    private var _worker = MutableLiveData<OompaLoompaDetail>()


    fun getWorkerDetail(id: Long?){
        viewModelScope.launch {
            _worker.value = workerDetailRepository.getWorkerDetail(id)
        }
    }
}