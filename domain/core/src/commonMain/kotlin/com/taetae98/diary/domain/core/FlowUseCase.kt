package com.taetae98.diary.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

public abstract class FlowUseCase<P, R> {
    public operator fun invoke(param: P): Flow<Result<R>> {
        return flow {
            emitAll(execute(param))
        }.catch {
            it.printStackTrace()
            emit(Result.failure(it))
        }
    }

    protected abstract fun execute(param: P): Flow<Result<R>>
}
