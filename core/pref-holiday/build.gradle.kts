plugins {
    id("diary.module.feature")
    id("diary.koin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.datetime)
                implementation(libs.datastore.core)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.pref.holiday"
}
