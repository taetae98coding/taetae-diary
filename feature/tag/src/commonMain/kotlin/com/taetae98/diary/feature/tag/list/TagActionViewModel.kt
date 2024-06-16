package com.taetae98.diary.feature.tag.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.domain.tag.usecase.UpdateTagDeleteUseCase
import com.taetae98.diary.domain.tag.usecase.UpdateTagFinishUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagActionViewModel(
    private val updateTagFinishUseCase: UpdateTagFinishUseCase,
    private val updateTagDeleteUseCase: UpdateTagDeleteUseCase,
) : ViewModel() {
    private val _message = MutableStateFlow<TagListActionMessage?>(null)
    val message = _message.asStateFlow()

    fun finish(id: String) {
        viewModelScope.launch {
            updateTagFinishUseCase(UpdateTagFinishUseCase.Param(id, true))
                .onSuccess {
                    val message = TagListActionMessage.Finish(
                        dismiss = ::dismissMessage,
                        cancel = { start(id) },
                    )
                    _message.emit(message)
                }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            updateTagDeleteUseCase(UpdateTagDeleteUseCase.Param(id, true))
                .onSuccess {
                    val message = TagListActionMessage.Delete(
                        dismiss = ::dismissMessage,
                        cancel = { restore(id) },
                    )
                    _message.emit(message)
                }
        }
    }

    private fun dismissMessage() {
        viewModelScope.launch {
            _message.emit(null)
        }
    }

    private fun start(id: String) {
        viewModelScope.launch {
            updateTagFinishUseCase(UpdateTagFinishUseCase.Param(id, false))
        }
    }

    private fun restore(id: String) {
        viewModelScope.launch {
            updateTagDeleteUseCase(UpdateTagDeleteUseCase.Param(id, false))
        }
    }
}
