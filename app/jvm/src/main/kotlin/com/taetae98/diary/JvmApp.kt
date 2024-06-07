package com.taetae98.diary

import androidx.compose.ui.window.singleWindowApplication
import com.taetae98.diary.app.App
import com.taetae98.diary.app.AppModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

internal fun main() {
    startKoin {
        modules(AppModule().module)
    }

    singleWindowApplication(
        title = "Diary"
    ) {
        App()
    }
}