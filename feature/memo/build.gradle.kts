plugins {
    id("diary.feature")
    id("diary.compose.test")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain:memo"))
                implementation(project(":domain:memo-tag"))
                implementation(project(":domain:tag"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.feature.memo"
}
