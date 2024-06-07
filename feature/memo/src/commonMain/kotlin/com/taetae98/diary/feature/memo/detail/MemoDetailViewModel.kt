package com.taetae98.diary.feature.memo.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.repository.MemoRepository
import com.taetae98.diary.domain.memo.usecase.UpdateMemoUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
    private val updateMemoUseCase: UpdateMemoUseCase,
) : ViewModel() {
    val title = savedStateHandle.getStateFlow(TITLE, "")
    val description = savedStateHandle.getStateFlow(DESCRIPTION, "")

    fun setMemoId(memoId: String) {
        updateIfChanged()

        savedStateHandle[MEMO_ID] = memoId
        viewModelScope.launch {
            memoRepository.findById(memoId).firstOrNull()?.let { updateByMemo(it) }
        }
    }

    private fun updateByMemo(memo: Memo) {
        savedStateHandle[HAS_CHANGED] = false
        savedStateHandle[TITLE] = memo.title
        savedStateHandle[DESCRIPTION] = memo.description
    }

    fun setTitle(title: String) {
        savedStateHandle[HAS_CHANGED] = true
        savedStateHandle[TITLE] = title
    }

    fun setDescription(description: String) {
        savedStateHandle[HAS_CHANGED] = true
        savedStateHandle[DESCRIPTION] = description
    }

    fun updateIfChanged() {
        if (savedStateHandle.get<Boolean>(HAS_CHANGED) != true) return

        val param = UpdateMemoUseCase.Param(
            memoId = savedStateHandle.get<String>(MEMO_ID) ?: return,
            detail = MemoDetail(
                title = title.value,
                description = description.value,
            ),
        )

        viewModelScope.launch {
            updateMemoUseCase(param)
        }
    }

    companion object {
        private const val HAS_CHANGED = "hasChanged"

        private const val MEMO_ID = "memoId"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }
}