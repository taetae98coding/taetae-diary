plugins {
    id("diary.module.feature")
    id("diary.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.ui)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.activity.compose)
                implementation(libs.material3.adaptive)
                implementation(libs.material3.adaptive.layout)
                implementation(libs.material3.adaptive.navigation)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.navigation.compose)
            }
        }

        jvmMain {
            dependencies {
                implementation(compose.foundation)
                implementation(libs.material3.windowsize)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "${Build.NAMESPACE}.library.compose.adaptive"
}
