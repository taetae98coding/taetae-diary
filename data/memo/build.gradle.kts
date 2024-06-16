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
                implementation(project(":library:paging3"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.data.memo"
}
