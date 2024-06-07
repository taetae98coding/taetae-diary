package org.koin.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
public actual inline fun <reified T : ViewModel> koinNavViewModel(): T {
    return org.koin.androidx.compose.navigation.koinNavViewModel()
}
