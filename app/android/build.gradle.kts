plugins {
    id("diary.android.application")
    id("diary.compose")
}

android {
    namespace = Build.NAMESPACE

    defaultConfig {
        applicationId = "com.taetae98.diary"
    }
}

dependencies {
    implementation(project(":app:common"))
    implementation(libs.material)
    implementation(libs.activity.compose)
    implementation(libs.startup)

    implementation(compose.foundation)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
}
