package com.taetae98.diary

import com.taetae98.diary.app.AppModule
import com.taetae98.diary.core.api.holiday.HolidayApiModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.ksp.generated.module
import platform.Foundation.NSBundle

public fun init() {
    startKoin {
        modules(AppModule().module, holidayModule())
    }
}

private fun holidayModule(): Module {
    return module {
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_PROXY_URL)) { "" }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_URL)) { NSBundle.mainBundle.objectForInfoDictionaryKey("HolidayApiUrl") as String }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_KEY)) { NSBundle.mainBundle.objectForInfoDictionaryKey("HolidayApiKey") as String }
    }
}
