package org.koin.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.compose.currentKoinScope

@Composable
public actual inline fun <reified T : ViewModel> koinNavViewModel(): T {
    val viewModelStoreOwner = requireNotNull(LocalViewModelStoreOwner.current)

    return resolveViewModel(
        vmClass = T::class,
        viewModelStore = viewModelStoreOwner.viewModelStore,
        key = null,
        extras = defaultNavExtras(viewModelStoreOwner),
        qualifier = null,
        scope = currentKoinScope(),
        parameters = null
    )
}