package com.taetae98.diary.domain.memo.usecase

import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.domain.account.GetAccountUseCase
import com.taetae98.diary.domain.core.UseCase
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.exception.MemoTitleEmptyException
import com.taetae98.diary.domain.memo.repository.MemoRepository
import com.taetae98.diary.library.uuid.uuid
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Factory

@Factory
public class InsertMemoUseCase internal constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val repository: MemoRepository,
) : UseCase<MemoDetail, String>() {
    override suspend fun execute(param: MemoDetail): String {
        if (param.title.isEmpty()) throw MemoTitleEmptyException()

        val account = getAccountUseCase(Unit).first().getOrThrow()
        val memo = Memo(
            id = uuid(),
            title = param.title,
            description = param.description,
            isFinish = false,
            isDelete = false,
            owner = account.uid,
        )

        repository.upsert(memo)
        return memo.id
    }
}
