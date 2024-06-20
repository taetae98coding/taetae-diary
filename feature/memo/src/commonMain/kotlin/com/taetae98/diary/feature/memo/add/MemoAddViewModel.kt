package com.taetae98.diary.feature.memo.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.core.model.tag.SelectTag
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.exception.MemoTitleEmptyException
import com.taetae98.diary.domain.memo.tag.usecase.UpsertMemoTagUseCase
import com.taetae98.diary.domain.memo.usecase.InsertMemoUseCase
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import com.taetae98.diary.feature.memo.detail.MemoDetailMessage
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoAddViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val insertMemoUseCase: InsertMemoUseCase,
    findAllTagUseCase: FindAllTagUseCase,
    private val upsertMemoTagUseCase: UpsertMemoTagUseCase,
) : ViewModel() {
    val title = savedStateHandle.getStateFlow(TITLE, "")
    val description = savedStateHandle.getStateFlow(DESCRIPTION, "")

    private val tagIdSet = MutableStateFlow(emptySet<String>())
    private val tagAllList = findAllTagUseCase(Unit).mapLatest { it.getOrNull().orEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = emptyList(),
        )

    val tagList = combine(tagIdSet, tagAllList) { tagIdSet, tagAllList ->
        tagAllList.map {
            SelectTag(
                id = it.id,
                title = it.title,
                isSelected = it.id in tagIdSet,
            )
        }.toPersistentList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
        initialValue = persistentListOf(),
    )

    private val _message = MutableStateFlow<MemoDetailMessage?>(null)
    val message = _message.asStateFlow()

    fun setTitle(title: String) {
        savedStateHandle[TITLE] = title
    }

    fun setDescription(description: String) {
        savedStateHandle[DESCRIPTION] = description
    }

    fun updateMemoTag(tagId: String, isSelected: Boolean) {
        val set = buildSet {
            addAll(tagIdSet.value)
            if (isSelected) {
                add(tagId)
            } else {
                remove(tagId)
            }
        }

        viewModelScope.launch {
            tagIdSet.emit(set)
        }
    }

    fun add() {
        val memoDetail = MemoDetail(
            title = title.value,
            description = description.value,
        )

        viewModelScope.launch {
            insertMemoUseCase(memoDetail).onSuccess {
                upsertMemoTagUseCase(UpsertMemoTagUseCase.Param(it, tagIdSet.value))
                clear()
                _message.emit(MemoDetailMessage.Add(::dismissMessage))
            }.onFailure {
                handleError(it)
            }
        }
    }

    fun clear() {
        setTitle("")
        setDescription("")
        viewModelScope.launch { tagIdSet.emit(emptySet()) }
    }

    private suspend fun handleError(throwable: Throwable) {
        when (throwable) {
            is MemoTitleEmptyException -> {
                _message.emit(MemoDetailMessage.TitleEmpty(::dismissMessage))
            }
        }
    }

    private fun dismissMessage() {
        viewModelScope.launch {
            _message.emit(null)
        }
    }

    companion object {
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }
}