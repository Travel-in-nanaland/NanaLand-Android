package com.nanaland.util.ui

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T, ) : UiState<T>
    data class Failure(val msg: String, ) : UiState<Nothing>
}

//when () {
//    is UiState.Loading -> {}
//    is UiState.Success -> {}
//    is UiState.Failure -> {}
//}