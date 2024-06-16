package com.taetae98.diary.domain.tag.exception

public class TagTitleEmptyException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
