package com.taetae98.diary.core.database.diary

import com.taetae98.diary.core.database.diary.memo.MemoEntity
import com.taetae98.diary.core.database.diary.memo.tag.MemoTagEntity
import com.taetae98.diary.core.database.diary.tag.TagEntity
import com.taetae98.diary.core.model.memo.Memo
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.model.tag.Tag

public fun Memo.toEntity(): MemoEntity {
    return MemoEntity(
        id = id,
        title = title,
        description = description,
        isFinish = isFinish,
        isDelete = isDelete,
        owner = owner,
    )
}

public fun MemoEntity.toModel(): Memo {
    return Memo(
        id = id,
        title = title,
        description = description,
        isFinish = isFinish,
        isDelete = isDelete,
        owner = owner,
    )
}

public fun Tag.toEntity(): TagEntity {
    return TagEntity(
        id = id,
        title = title,
        description = description,
        isMemoFilter = isMemoFilter,
        isFinish = isFinish,
        isDelete = isDelete,
        owner = owner,
    )
}

public fun TagEntity.toModel(): Tag {
    return Tag(
        id = id,
        title = title,
        description = description,
        isMemoFilter = isMemoFilter,
        isFinish = isFinish,
        isDelete = isDelete,
        owner = owner,
    )
}

public fun MemoTag.toEntity(): MemoTagEntity {
    return MemoTagEntity(
        memoId = memoId,
        tagId = tagId
    )
}