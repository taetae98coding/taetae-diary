import org.jetbrains.compose.internal.utils.getLocalProperty

plugins {
    id("diary.android.application")
    id("diary.compose")
}

android {
    namespace = Build.NAMESPACE

    defaultConfig {
        applicationId = "com.taetae98.diary"
        versionCode = 4
        versionName = "1.2.0"
    }

    signingConfigs {
        create("dev") {
            keyAlias = getLocalProperty("android.dev.signing.key.alias")
            keyPassword = getLocalProperty("android.dev.signing.key.password")
            storeFile = file("keystore/dev.jks")
            storePassword = getLocalProperty("android.dev.signing.store.password")
        }

        create("real") {
            keyAlias = getLocalProperty("android.real.signing.key.alias")
            keyPassword = getLocalProperty("android.real.signing.key.password")
            storeFile = file("keystore/real.jks")
            storePassword = getLocalProperty("android.real.signing.store.password")
        }
    }

    flavorDimensions.add("product")
    productFlavors {
        create("dev") {
            dimension = "product"
            isDefault = true
            applicationIdSuffix = ".dev"
            signingConfig = signingConfigs.getByName("dev")

            buildConfigField("String", "HOLIDAY_API_URL", "\"${getLocalProperty("holiday.dev.api.url")}\"")
            buildConfigField("String", "HOLIDAY_API_KEY", "\"${getLocalProperty("holiday.dev.api.key")}\"")
        }

        create("real") {
            dimension = "product"
            signingConfig = signingConfigs.getByName("real")

            buildConfigField("String", "HOLIDAY_API_URL", "\"${getLocalProperty("holiday.real.api.url")}\"")
            buildConfigField("String", "HOLIDAY_API_KEY", "\"${getLocalProperty("holiday.real.api.key")}\"")
        }
    }

    buildTypes {
        debug {
            isDefault = true
            applicationIdSuffix = ".debug"
        }

        release {
            isShrinkResources = true
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":app:common"))
    implementation(project(":core:api-holiday"))

    implementation(libs.material)
    implementation(libs.activity.compose)
    implementation(libs.startup)

    implementation(compose.foundation)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
}
