import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.Family

plugins {
    id("diary.ios")
    id("diary.compose")
}

kotlin {
    targets.filterIsInstance<KotlinNativeTarget>()
        .filter { it.konanTarget.family == Family.IOS }
        .forEach { target ->
            target.binaries.framework {
                baseName = "iosApp"
            }
        }

    sourceSets {
        iosMain {
            dependencies {
                implementation(project(":app:common"))
                implementation(project(":core:api-holiday"))
                implementation(compose.ui)
            }
        }
    }
}
