plugins {
    id("diary.module.feature")
    id("diary.compose")
    id("diary.compose.test")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.markdown)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.compose"
}
