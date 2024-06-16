plugins {
    id("diary.module.feature")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.paging.common)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.library.paging3"
}
