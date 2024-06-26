plugins {
    id("diary.module.common")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.datetime)
            }
        }
    }
}