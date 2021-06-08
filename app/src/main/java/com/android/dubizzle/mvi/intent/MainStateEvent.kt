package com.android.dubizzle.mvi.intent

sealed class MainStateEvent {

    object GetList: MainStateEvent()
    object None: MainStateEvent()
}