plugins {
    id("diary.module.common")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.navigation.common)
            }
        }
    }
}
