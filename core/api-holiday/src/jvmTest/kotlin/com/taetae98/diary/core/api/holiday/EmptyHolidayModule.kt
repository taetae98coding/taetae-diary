package com.taetae98.diary.core.api.holiday

import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

fun emptyHolidayModule(): Module {
    return module {
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_PROXY_URL)) { "" }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_URL)) { "" }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_KEY)) { "" }
    }
}