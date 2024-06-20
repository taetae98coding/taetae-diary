package com.taetae98.diary.app

import com.taetae98.diary.core.coroutines.CoroutinesModule
import com.taetae98.diary.core.database.diary.DiaryDatabaseModule
import com.taetae98.diary.data.memo.MemoDataModule
import com.taetae98.diary.data.memo.tag.MemoTagDataModule
import com.taetae98.diary.data.tag.TagDataModule
import com.taetae98.diary.domain.account.AccountDomainModule
import com.taetae98.diary.domain.memo.MemoDomainModule
import com.taetae98.diary.domain.memo.tag.MemoTagDomainModule
import com.taetae98.diary.domain.tag.TagDomainModule
import com.taetae98.diary.feature.memo.MemoFeatureModule
import com.taetae98.diary.feature.tag.TagFeatureModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        CoroutinesModule::class,
        DiaryDatabaseModule::class,
        MemoDataModule::class,
        MemoTagDataModule::class,
        TagDataModule::class,
        AccountDomainModule::class,
        MemoDomainModule::class,
        MemoTagDomainModule::class,
        TagDomainModule::class,
        MemoFeatureModule::class,
        TagFeatureModule::class,
    ],
)
public class AppModule
