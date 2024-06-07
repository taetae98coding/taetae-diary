plugins {
    id("diary.feature")
    id("diary.compose.test")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain:memo"))
                implementation(libs.lifecycle.viewmodel.savedstate)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.feature.memo"
}
