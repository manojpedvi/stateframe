package com.manojpedvi.stateframe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun StateFrameLoadingPreview() {
    MaterialTheme {
        Surface {
            StateFrame<String>(state = AsyncUiState.Loading) {
                Text(text = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StateFrameEmptyPreview() {
    MaterialTheme {
        Surface {
            StateFrame<String>(
                state = AsyncUiState.Empty(
                    title = "No products found",
                    message = "Try changing your filters.",
                ),
            ) {
                Text(text = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StateFrameErrorPreview() {
    MaterialTheme {
        Surface {
            StateFrame<String>(
                state = AsyncUiState.Error(
                    title = "Unable to load products",
                    message = "Check your internet connection and try again.",
                ),
                onRetry = {},
            ) {
                Text(text = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StateFrameSuccessPreview() {
    MaterialTheme {
        Surface {
            StateFrame(
                state = AsyncUiState.Success("Loaded content"),
            ) { message ->
                Text(
                    text = message,
                    modifier = Modifier.padding(24.dp),
                )
            }
        }
    }
}
