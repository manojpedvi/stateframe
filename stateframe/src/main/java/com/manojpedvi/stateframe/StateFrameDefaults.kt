package com.manojpedvi.stateframe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

object StateFrameDefaults {
    @Composable
    fun Loading(
        modifier: Modifier = Modifier,
        message: String = "Loading...",
    ) {
        Column(
            modifier = modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
    }

    @Composable
    fun Empty(
        title: String,
        message: String,
        modifier: Modifier = Modifier,
        icon: ImageVector = Icons.Default.Info,
        actionLabel: String? = null,
        onAction: (() -> Unit)? = null,
    ) {
        StateMessage(
            title = title,
            message = message,
            modifier = modifier,
            icon = icon,
            iconTint = MaterialTheme.colorScheme.primary,
            actionLabel = actionLabel,
            onAction = onAction,
        )
    }

    @Composable
    fun Error(
        title: String,
        message: String,
        modifier: Modifier = Modifier,
        icon: ImageVector = Icons.Default.Warning,
        retryLabel: String = "Try again",
        onRetry: (() -> Unit)? = null,
    ) {
        StateMessage(
            title = title,
            message = message,
            modifier = modifier,
            icon = icon,
            iconTint = MaterialTheme.colorScheme.error,
            titleColor = MaterialTheme.colorScheme.error,
            actionLabel = retryLabel,
            onAction = onRetry,
        )
    }

    @Composable
    private fun StateMessage(
        title: String,
        message: String,
        modifier: Modifier,
        icon: ImageVector,
        iconTint: androidx.compose.ui.graphics.Color,
        titleColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
        actionLabel: String? = null,
        onAction: (() -> Unit)? = null,
    ) {
        Column(
            modifier = modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = iconTint,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = titleColor,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )

            if (actionLabel != null && onAction != null) {
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = onAction) {
                    Text(text = actionLabel)
                }
            }
        }
    }
}
