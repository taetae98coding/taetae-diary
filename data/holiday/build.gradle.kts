plugins {
    id("diary.module.feature")
    id("diary.koin")
    id("diary.kotest")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:pref-holiday"))
                implementation(project(":core:database-holiday"))
                implementation(project(":core:api-holiday"))
                implementation(project(":domain:holiday"))
            }
        }

        commonTest {
            dependencies {
                implementation(project(":core:test"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.data.holiday"
}
