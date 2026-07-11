package com.manojpedvi.stateframe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Renders one of the standard async UI states and delegates successful content
 * to the caller.
 */
@Composable
fun <T> StateFrame(
    state: AsyncUiState<T>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onRetry: (() -> Unit)? = null,
    loadingContent: @Composable BoxScope.() -> Unit = {
        StateFrameDefaults.Loading()
    },
    emptyContent: @Composable BoxScope.(AsyncUiState.Empty) -> Unit = { empty ->
        StateFrameDefaults.Empty(
            title = empty.title,
            message = empty.message,
        )
    },
    errorContent: @Composable BoxScope.(AsyncUiState.Error) -> Unit = { error ->
        StateFrameDefaults.Error(
            title = error.title,
            message = error.message,
            onRetry = onRetry,
        )
    },
    content: @Composable BoxScope.(T) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = contentAlignment,
    ) {
        when (state) {
            AsyncUiState.Loading -> loadingContent()
            is AsyncUiState.Empty -> emptyContent(state)
            is AsyncUiState.Error -> errorContent(state)
            is AsyncUiState.Success -> content(state.data)
        }
    }
}
