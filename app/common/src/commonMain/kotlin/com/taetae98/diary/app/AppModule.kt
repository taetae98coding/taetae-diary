package com.taetae98.diary.app

import com.taetae98.diary.core.coroutines.CoroutinesModule
import com.taetae98.diary.core.database.diary.DiaryDatabaseModule
import com.taetae98.diary.data.memo.MemoDataModule
import com.taetae98.diary.domain.account.AccountDomainModule
import com.taetae98.diary.domain.memo.MemoDomainModule
import com.taetae98.diary.feature.memo.MemoFeatureModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        CoroutinesModule::class,
        DiaryDatabaseModule::class,
        MemoDataModule::class,
        AccountDomainModule::class,
        MemoDomainModule::class,
        MemoFeatureModule::class,
    ],
)
public class AppModule
