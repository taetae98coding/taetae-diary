plugins {
    id("diary.module.common")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:model"))
            }
        }
    }
}
