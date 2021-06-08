package com.android.dubizzle.mvi.model

data class DataApiResponse(
    val pagination: Pagination?,
    val results: List<Result>?
)