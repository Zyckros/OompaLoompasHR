package com.example.oompaloompashr.data

import androidx.lifecycle.LiveData
import com.example.oompaloompashr.api.HRService
import com.example.oompaloompashr.model.OompaLoompaDetail

class WorkerDetailRepository(private val service: HRService) {

    suspend fun getWorkerDetail(id: Long?): OompaLoompaDetail{
        return service.fetchWorkerDetail(id)
    }
}