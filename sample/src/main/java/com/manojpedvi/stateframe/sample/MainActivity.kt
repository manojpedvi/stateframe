package com.manojpedvi.stateframe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.manojpedvi.stateframe.AsyncUiState
import com.manojpedvi.stateframe.StateFrame

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    StateFrameDemo()
                }
            }
        }
    }
}

@Composable
private fun StateFrameDemo() {
    var stateName by rememberSaveable { mutableStateOf("loading") }
    val products = listOf(
        Product("Trail Backpack", "$84", "Ready for weekend hikes"),
        Product("Daily Sneakers", "$120", "Lightweight knit upper"),
        Product("Desk Lamp", "$42", "Warm dimmable light"),
    )
    val state: AsyncUiState<List<Product>> = when (stateName) {
        "empty" -> AsyncUiState.Empty(
            title = "No products found",
            message = "Try another category or clear filters.",
        )
        "error" -> AsyncUiState.Error(
            title = "Unable to load products",
            message = "Check your connection and try again.",
        )
        "success" -> AsyncUiState.Success(products)
        else -> AsyncUiState.Loading
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "StateFrame",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Reusable Compose UI for loading, empty, error, and content states.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                StateButtons(
                    selected = stateName,
                    onSelected = { stateName = it },
                )
            }
        },
    ) { padding ->
        StateFrame(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            onRetry = { stateName = "loading" },
        ) { items ->
            ProductList(products = items)
        }
    }
}

@Composable
private fun StateButtons(
    selected: String,
    onSelected: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        listOf("loading", "empty", "error", "success").forEach { name ->
            val label = name.replaceFirstChar { it.uppercase() }
            if (selected == name) {
                Button(onClick = { onSelected(name) }) {
                    Text(text = label)
                }
            } else {
                OutlinedButton(onClick = { onSelected(name) }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
private fun ProductList(products: List<Product>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Composable
private fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

private data class Product(
    val name: String,
    val price: String,
    val description: String,
)
