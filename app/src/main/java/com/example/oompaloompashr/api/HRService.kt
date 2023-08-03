/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.oompaloompashr.api

import com.example.oompaloompashr.model.OompaLoompaDetail
import com.example.oompaloompashr.model.WorkersList
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"
/**
 * Github API communication setup via Retrofit.
 */
interface HRService {
    /**
     * Get repos ordered by stars.
     */
    @GET("oompa-loompas")
    suspend fun searchWorkers(
        @Query("page") page: Int
    ): WorkersList

    @GET("oompa-loompas/{id}")
    suspend fun fetchWorkerDetail(
        @Path("id") id: Long?
    ): OompaLoompaDetail

    companion object {
        private const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"

        fun create(): HRService {
            val client = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HRService::class.java)
        }
    }
}
