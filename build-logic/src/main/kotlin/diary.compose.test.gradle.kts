
import ext.android
import ext.kotlinMultiplatform
import ext.libs
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

kotlinMultiplatform {
    sourceSets.all {
        languageSettings.optIn("androidx.compose.ui.test.ExperimentalTestApi")
    }

    sourceSets {
        getByName("androidUnitTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.findLibrary("mockk").get())
                implementation(libs.findLibrary("robolectric").get())

                implementation(project.dependencies.platform(libs.findLibrary("compose-bom").get()))
                implementation(libs.findLibrary("compose-ui-test-junit4").get())
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    sourceSets.invokeWhenCreated("androidDebug") {
        dependencies {
            implementation(project.dependencies.platform(libs.findLibrary("compose-bom").get()))
            implementation(libs.findLibrary("compose-ui-test-manifest").get())
        }
    }
}

android {
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
