package com.jeju.nanaland.util.ui

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T, ) : UiState<T>
    data class Failure(val msg: String, val e: Throwable? = null) : UiState<Nothing>
}