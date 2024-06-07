plugins {
    id("diary.module.feature")
    id("diary.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.activity.compose)
            }
        }

        val nonAndroidMain = create("nonAndroidMain")

        nonAndroidMain.dependsOn(commonMain.get())
        iosMain.get().dependsOn(nonAndroidMain)
        jvmMain.get().dependsOn(nonAndroidMain)
    }
}

android {
    namespace = "${Build.NAMESPACE}.library.compose.back"
}
