package com.taetae98.diary

import androidx.compose.ui.window.singleWindowApplication
import com.taetae98.diary.app.App
import com.taetae98.diary.app.AppModule
import com.taetae98.diary.core.api.holiday.HolidayApiModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.ksp.generated.module

internal fun main(args: Array<String>) {
    startKoin {
        modules(
            AppModule().module,
            holidayModule(args[0], args[1]),
        )
    }

    singleWindowApplication(
        title = "Diary",
    ) {
        App()
    }
}

private fun holidayModule(
    apiUrl: String,
    apiKey: String,
): Module {
    return module {
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_PROXY_URL)) { "" }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_URL)) { apiUrl }
        single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_KEY)) { apiKey }
    }
}
