package com.taetae98.diary.feature.memo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.domain.memo.usecase.UpdateMemoDeleteUseCase
import com.taetae98.diary.domain.memo.usecase.UpdateMemoFinishUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoActionViewModel(
    private val updateMemoFinishUseCase: UpdateMemoFinishUseCase,
    private val updateMemoDeleteUseCase: UpdateMemoDeleteUseCase,
) : ViewModel() {
    private val _message = MutableStateFlow<MemoListActionMessage?>(null)
    val message = _message.asStateFlow()

    fun finish(id: String) {
        viewModelScope.launch {
            updateMemoFinishUseCase(UpdateMemoFinishUseCase.Param(id, true))
                .onSuccess {
                    val message = MemoListActionMessage.Finish(
                        dismiss = ::dismissMessage,
                        cancel = { start(id) },
                    )
                    _message.emit(message)
                }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            updateMemoDeleteUseCase(UpdateMemoDeleteUseCase.Param(id, true))
                .onSuccess {
                    val message = MemoListActionMessage.Delete(
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
            updateMemoFinishUseCase(UpdateMemoFinishUseCase.Param(id, false))
        }
    }

    private fun restore(id: String) {
        viewModelScope.launch {
            updateMemoDeleteUseCase(UpdateMemoDeleteUseCase.Param(id, false))
        }
    }
}
