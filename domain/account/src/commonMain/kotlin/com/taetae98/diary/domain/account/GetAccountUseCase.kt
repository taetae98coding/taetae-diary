package com.taetae98.diary.domain.account

import com.taetae98.diary.core.model.account.Account
import com.taetae98.diary.domain.core.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory

@Factory
public class GetAccountUseCase internal constructor(

) : FlowUseCase<Unit, Account>() {
    override fun execute(param: Unit): Flow<Result<Account>> {
        return flowOf(Result.success(Account.Guest))
    }
}