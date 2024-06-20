plugins {
    id("diary.feature")
    id("diary.compose.test")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain:holiday"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.feature.calendar"
}
