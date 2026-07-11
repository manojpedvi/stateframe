# StateFrame

StateFrame is a small Jetpack Compose library for rendering common async UI states:
loading, empty, error, and success content.

```kotlin
StateFrame(
    state = uiState,
    onRetry = viewModel::loadProducts,
) { products ->
    ProductList(products)
}
```

## Package

```kotlin
com.manojpedvi.stateframe
```

## Why

Most Android screens repeat the same state handling:

```kotlin
when {
    isLoading -> LoadingUi()
    error != null -> ErrorUi()
    items.isEmpty() -> EmptyUi()
    else -> ContentUi(items)
}
```

StateFrame centralizes that pattern while still allowing custom UI for every state.

## API

```kotlin
sealed interface AsyncUiState<out T> {
    data object Loading : AsyncUiState<Nothing>
    data class Success<T>(val data: T) : AsyncUiState<T>
    data class Empty(val title: String, val message: String) : AsyncUiState<Nothing>
    data class Error(
        val title: String,
        val message: String,
        val throwable: Throwable? = null,
    ) : AsyncUiState<Nothing>
}
```

```kotlin
@Composable
fun <T> StateFrame(
    state: AsyncUiState<T>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onRetry: (() -> Unit)? = null,
    loadingContent: @Composable BoxScope.() -> Unit = { StateFrameDefaults.Loading() },
    emptyContent: @Composable BoxScope.(AsyncUiState.Empty) -> Unit = { ... },
    errorContent: @Composable BoxScope.(AsyncUiState.Error) -> Unit = { ... },
    content: @Composable BoxScope.(T) -> Unit,
)
```

## Custom state UI

```kotlin
StateFrame(
    state = uiState,
    emptyContent = { empty ->
        StateFrameDefaults.Empty(
            title = empty.title,
            message = empty.message,
            actionLabel = "Add product",
            onAction = onAddProduct,
        )
    },
    errorContent = { error ->
        StateFrameDefaults.Error(
            title = error.title,
            message = error.message,
            retryLabel = "Retry",
            onRetry = onRetry,
        )
    },
) { products ->
    ProductList(products)
}
```

## Modules

- `:stateframe` - reusable Compose library
- `:sample` - demo app showing loading, empty, error, and success states

## Local publishing

```bash
./gradlew :stateframe:publishToMavenLocal
```

The initial Maven coordinates are:

```kotlin
implementation("com.manojpedvi:stateframe:0.1.0")
```
