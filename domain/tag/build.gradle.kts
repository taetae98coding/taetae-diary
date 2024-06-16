plugins {
    id("diary.module.common")
    id("diary.koin")
    id("diary.kotest")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain:account"))
                implementation(project(":library:uuid"))
                implementation(libs.paging.common)

                api(project(":core:model"))
                api(project(":domain:core"))
            }
        }
    }
}
