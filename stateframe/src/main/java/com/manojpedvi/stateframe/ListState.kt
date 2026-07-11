package com.manojpedvi.stateframe

fun <T> List<T>.toAsyncUiState(
    emptyTitle: String = "No data found",
    emptyMessage: String = "There is nothing to show right now.",
): AsyncUiState<List<T>> {
    return if (isEmpty()) {
        AsyncUiState.Empty(
            title = emptyTitle,
            message = emptyMessage,
        )
    } else {
        AsyncUiState.Success(this)
    }
}
