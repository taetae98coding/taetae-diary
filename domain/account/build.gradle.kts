plugins {
    id("diary.module.common")
    id("diary.koin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:model"))
                implementation(libs.coroutines.core)

                api(project(":domain:core"))
            }
        }
    }
}
