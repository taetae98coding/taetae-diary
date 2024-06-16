package com.taetae98.diary.feature.tag.list.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagListDetailViewModel : ViewModel() {
    private val _isAdd = MutableStateFlow(true)
    val isAdd = _isAdd.asStateFlow()

    fun setIsAdd(isAdd: Boolean) {
        viewModelScope.launch {
            _isAdd.emit(isAdd)
        }
    }
}
