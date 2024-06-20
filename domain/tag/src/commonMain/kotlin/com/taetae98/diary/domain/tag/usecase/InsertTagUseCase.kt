package com.taetae98.diary.domain.tag.usecase

import com.taetae98.diary.core.model.tag.Tag
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.exception.TagTitleEmptyException
import com.taetae98.diary.domain.tag.repository.TagRepository
import com.taetae98.diary.library.uuid.uuid
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Factory

@Factory
public class InsertTagUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val repository: TagRepository,
) : UseCase<TagDetail, Unit>() {
    override suspend fun execute(param: TagDetail) {
        if (param.title.isEmpty()) throw TagTitleEmptyException()

        val account = getAccountUseCase(Unit).first().getOrThrow()
        val tag = Tag(
            id = uuid(),
            title = param.title,
            description = param.description,
            isMemoFilter = false,
            isFinish = false,
            isDelete = false,
            owner = account.uid,
        )

        repository.upsert(tag)
    }
}
