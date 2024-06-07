plugins {
    id("diary.module.feature")
    id("diary.compose")
}

kotlin {
    sourceSets {
        jvmMain {
            dependencies {
                implementation(libs.material3.windowsize)
            }
        }

        val androidJvmMain = create("androidJvmMain") {
            dependencies {
                implementation(compose.ui)
                api(libs.material3.navigation.suite)
            }
        }

        androidJvmMain.dependsOn(commonMain.get())
        androidMain.get().dependsOn(androidJvmMain)
        jvmMain.get().dependsOn(androidJvmMain)
    }
}

android {
    namespace = "${Build.NAMESPACE}.library.compose.navigation.suite"
}
