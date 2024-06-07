plugins {
    id("diary.module.feature")
    id("diary.koin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:database-diary"))
                implementation(project(":domain:memo"))
                implementation(libs.paging.common)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.data.memo"
}
