package com.taetae98.diary.core.datetime

import kotlinx.datetime.Clock
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
public class DateTimeModule {
    @Singleton
    internal fun providesClock(): Clock {
        return Clock.System
    }
}
