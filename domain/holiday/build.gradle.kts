plugins {
    id("diary.module.common")
    id("diary.koin")
    id("diary.kotest")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":core:model"))
                api(project(":domain:core"))
            }
        }
    }
}
