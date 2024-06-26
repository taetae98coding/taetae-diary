package com.taetae98.diary.startup

import android.content.Context
import androidx.startup.Initializer
import com.taetae98.diary.BuildConfig
import com.taetae98.diary.app.AppModule
import com.taetae98.diary.core.api.holiday.HolidayApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.ksp.generated.module

internal class KoinStartup : Initializer<Unit> {
    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            modules(AppModule().module, buildConfigModule())
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    private fun buildConfigModule(): Module {
        return module {
            single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_PROXY_URL)) { if (BuildConfig.DEBUG) "" else getProxyUrl() }
            single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_URL)) { BuildConfig.HOLIDAY_API_URL }
            single(qualifier = StringQualifier(HolidayApiModule.HOLIDAY_API_KEY)) { BuildConfig.HOLIDAY_API_KEY }
        }
    }

    private fun getProxyUrl(): String {
        val protocol = when {
            !System.getProperty("http.proxyHost").isNullOrEmpty() -> "http"
            !System.getProperty("https.proxyHost").isNullOrEmpty() -> "https"
            else -> return ""
        }

        val host = System.getProperty("$protocol.proxyHost")
        val port = System.getProperty("$protocol.proxyPort")

        return "$protocol://$host:$port/"
    }
}