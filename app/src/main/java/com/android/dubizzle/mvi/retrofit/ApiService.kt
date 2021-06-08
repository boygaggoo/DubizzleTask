package com.android.dubizzle.mvi.retrofit

import com.android.dubizzle.mvi.model.DataApiResponse
import com.android.dubizzle.mvi.util.Constants.GET_LIST
import retrofit2.http.GET

interface ApiService {
    @GET(GET_LIST)
    suspend fun getAdList(): DataApiResponse
}