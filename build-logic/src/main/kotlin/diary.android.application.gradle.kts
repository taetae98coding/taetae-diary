import ext.androidApplication

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("diary.android")
    id("diary.kotlin")
}

androidApplication {
    defaultConfig {
        targetSdk = Build.ANDROID_TARGET_SDK
    }
}
