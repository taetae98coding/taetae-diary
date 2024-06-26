import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.internal.utils.getLocalProperty

plugins {
    id("diary.jvm")
    id("diary.compose")
}

dependencies {
    implementation(project(":app:common"))
    implementation(project(":core:api-holiday"))
    implementation(compose.ui)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    runtimeOnly(compose.desktop.currentOs)
}

// https://github.com/JetBrains/compose-multiplatform/tree/master/tutorials/Native_distributions_and_local_execution
compose.desktop {
    application {
        mainClass = "com.taetae98.diary.JvmAppKt"
        args(
            getLocalProperty("holiday.dev.api.url").orEmpty(),
            getLocalProperty("holiday.dev.api.key").orEmpty()
        )

        nativeDistributions {
            targetFormats(TargetFormat.Dmg)

            packageName = "Diary"
            packageVersion = "1.2.0"

            macOS {
                bundleID = "com.taetae98.diary"
            }
        }
    }
}
