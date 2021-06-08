package com.android.dubizzle.mvi.ui


import androidx.lifecycle.*
import com.android.dubizzle.mvi.intent.MainStateEvent
import com.android.dubizzle.mvi.model.DataApiResponse
import com.android.dubizzle.mvi.repository.DataRepository
import com.android.dubizzle.mvi.util.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: DataRepository
): ViewModel() {


    private val _dataState: MutableLiveData<DataState<DataApiResponse>> = MutableLiveData()
    val dataState: LiveData<DataState<DataApiResponse>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetList -> {
                    mainRepository.getAdsList()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    //Do anything
                }
            }
        }
    }
}
