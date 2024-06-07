package com.taetae98.diary.domain.memo.exception

public class MemoTitleEmptyException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)