package com.taetae98.diary.app

import com.taetae98.diary.core.api.holiday.HolidayApiModule
import com.taetae98.diary.core.coroutines.CoroutinesModule
import com.taetae98.diary.core.database.diary.DiaryDatabaseModule
import com.taetae98.diary.core.database.holiday.HolidayDatabaseModule
import com.taetae98.diary.core.datetime.DateTimeModule
import com.taetae98.diary.core.pref.holiday.HolidayPrefModule
import com.taetae98.diary.data.holiday.HolidayDataModule
import com.taetae98.diary.data.memo.MemoDataModule
import com.taetae98.diary.data.memo.tag.MemoTagDataModule
import com.taetae98.diary.data.tag.TagDataModule
import com.taetae98.diary.domain.account.AccountDomainModule
import com.taetae98.diary.domain.holiday.HolidayDomainModule
import com.taetae98.diary.domain.memo.MemoDomainModule
import com.taetae98.diary.domain.memo.tag.MemoTagDomainModule
import com.taetae98.diary.domain.tag.TagDomainModule
import com.taetae98.diary.feature.calendar.CalendarFeatureModule
import com.taetae98.diary.feature.memo.MemoFeatureModule
import com.taetae98.diary.feature.tag.TagFeatureModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        CoroutinesModule::class,
        DateTimeModule::class,
        HolidayPrefModule::class,
        HolidayApiModule::class,
        HolidayDatabaseModule::class,
        DiaryDatabaseModule::class,
        MemoDataModule::class,
        MemoTagDataModule::class,
        HolidayDataModule::class,
        TagDataModule::class,
        AccountDomainModule::class,
        MemoDomainModule::class,
        MemoTagDomainModule::class,
        HolidayDomainModule::class,
        TagDomainModule::class,
        MemoFeatureModule::class,
        TagFeatureModule::class,
        CalendarFeatureModule::class,
    ],
)
public class AppModule
