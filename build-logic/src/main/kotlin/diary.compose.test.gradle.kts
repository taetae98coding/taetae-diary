
import ext.android
import ext.compose
import ext.kotlinMultiplatform
import ext.libs
import org.jetbrains.compose.ExperimentalComposeLibrary
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
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
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
