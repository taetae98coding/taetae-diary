package com.taetae98.diary.domain.core

public abstract class UseCase<P, R> {
    public suspend operator fun invoke(param: P): Result<R> {
        return runCatching { execute(param) }
    }

    protected abstract suspend fun execute(param: P): R
}
