package com.taetae98.diary.feature.tag.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.repository.TagRepository
import com.taetae98.diary.domain.tag.usecase.UpdateTagUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val tagRepository: TagRepository,
    private val updateTagUseCase: UpdateTagUseCase,
) : ViewModel() {
    val id = savedStateHandle.getStateFlow(TAG_ID, "")
    val title = savedStateHandle.getStateFlow(TITLE, "")
    val description = savedStateHandle.getStateFlow(DESCRIPTION, "")

    fun setTagId(tagId: String) {
        updateIfChanged()

        savedStateHandle[TAG_ID] = tagId
        viewModelScope.launch {
            tagRepository.findById(tagId).firstOrNull()?.let { updateByTag(it) }
        }
    }

    private fun updateByTag(tag: Tag) {
        savedStateHandle[HAS_CHANGED] = false
        savedStateHandle[TITLE] = tag.title
        savedStateHandle[DESCRIPTION] = tag.description
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

        val param = UpdateTagUseCase.Param(
            tagId = savedStateHandle.get<String>(TAG_ID) ?: return,
            detail = TagDetail(
                title = title.value,
                description = description.value,
            ),
        )

        viewModelScope.launch {
            updateTagUseCase(param)
        }
    }

    companion object {
        private const val HAS_CHANGED = "hasChanged"

        private const val TAG_ID = "tagId"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }
}
