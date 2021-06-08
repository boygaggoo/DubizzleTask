package com.android.dubizzle.mvi.repository

import android.util.Log
import com.android.dubizzle.mvi.model.DataApiResponse
import com.android.dubizzle.mvi.retrofit.ApiService
import com.android.dubizzle.mvi.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRepository constructor(
    private val apiService: ApiService,
) {

    private val TAG = "MainRepository"

    suspend fun getAdsList(): Flow<DataState<DataApiResponse>> = flow {
        Log.d(TAG, "getManga")
        //Show loading.
        emit(DataState.Loading)
        try {
            //Get from network.
            var networkUpcomingEvents = apiService.getAdList()
            Log.d(TAG, "getUpcomingEvents : " + networkUpcomingEvents)
            //Show success with saved data.
            emit(DataState.Success(networkUpcomingEvents))
        } catch (e: Exception) {
            e.printStackTrace()
            //Show error.
            emit(DataState.Error(e))
        }
    }

}