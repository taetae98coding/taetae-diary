plugins {
    id("diary.module.feature")
    id("diary.koin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:database-diary"))
                implementation(project(":domain:tag"))
                implementation(project(":library:paging3"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.data.tag"
}
