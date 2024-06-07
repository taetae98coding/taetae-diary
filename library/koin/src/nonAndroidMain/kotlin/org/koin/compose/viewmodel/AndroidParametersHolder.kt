package org.koin.compose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.ParametersHolder

@Suppress("UNCHECKED_CAST")
internal class AndroidParametersHolder(
    initialValues: ParametersDefinition? = null,
    private val extras: CreationExtras,
) : ParametersHolder(initialValues?.invoke()?.values?.toMutableList() ?: mutableListOf()) {

    override fun <T> elementAt(i: Int, clazz: KClass<*>): T {
        return createSavedStateHandleOrElse(clazz) { super.elementAt(i, clazz) }
    }

    override fun <T> getOrNull(clazz: KClass<*>): T? {
        return createSavedStateHandleOrElse(clazz) { super.getOrNull(clazz) }
    }

    private fun <T> createSavedStateHandleOrElse(clazz: KClass<*>, block: () -> T): T {
        return if (clazz == SavedStateHandle::class) {
            extras.createSavedStateHandle() as T
        } else block()
    }
}