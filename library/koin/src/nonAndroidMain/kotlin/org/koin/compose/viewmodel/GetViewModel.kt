package org.koin.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

public fun <T : ViewModel> resolveViewModel(
    vmClass: KClass<T>,
    viewModelStore: ViewModelStore,
    key: String? = null,
    extras: CreationExtras,
    qualifier: Qualifier? = null,
    scope: Scope,
    parameters: ParametersDefinition? = null,
): T {
    val factory = KoinViewModelFactory(vmClass, scope, qualifier, parameters)
    val provider = ViewModelProvider.create(viewModelStore, factory, extras)
    val vmKey = getViewModelKey(qualifier, key, vmClass.qualifiedName)
    return when {
        vmKey != null -> provider[vmKey, vmClass]
        else -> provider[vmClass]
    }
}

internal fun getViewModelKey(qualifier: Qualifier? = null, key: String? = null, className: String? = null): String? {
    return when {
        key != null -> key
        qualifier != null -> qualifier.value + (className?.let { "_$className" } ?: "")
        else -> null
    }
}