package com.manojpedvi.stateframe

/**
 * Represents the common lifecycle of async screen content.
 */
sealed interface AsyncUiState<out T> {
    data object Loading : AsyncUiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : AsyncUiState<T>

    data class Empty(
        val title: String = "No data found",
        val message: String = "There is nothing to show right now.",
    ) : AsyncUiState<Nothing>

    data class Error(
        val title: String = "Something went wrong",
        val message: String = "Please try again.",
        val throwable: Throwable? = null,
    ) : AsyncUiState<Nothing>
}
