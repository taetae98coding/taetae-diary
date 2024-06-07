plugins {
    id("diary.module.feature")
    id("diary.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(libs.lifecycle.viewmodel)
                implementation(compose.runtime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.androidx.compose.navigation)
            }
        }

        val nonAndroidMain = create("nonAndroidMain") {
            dependencies {
                implementation(libs.koin.compose)
                implementation(libs.navigation.common)
                implementation(libs.lifecycle.viewmodel.compose)
            }
        }

        nonAndroidMain.dependsOn(commonMain.get())
        iosMain.get().dependsOn(nonAndroidMain)
        jvmMain.get().dependsOn(nonAndroidMain)
    }
}

android {
    namespace = "${Build.NAMESPACE}.library.koin"
}
