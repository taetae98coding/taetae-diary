package org.koin.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
public expect inline fun <reified T : ViewModel> koinNavViewModel(): T