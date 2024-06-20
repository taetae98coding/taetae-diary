
import ext.compose
import ext.kotlinMultiplatform
import ext.libs

plugins {
    id("diary.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("diary.kotlin")
    id("diary.compose")
    id("diary.koin")
}

kotlinMultiplatform {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:compose"))
                implementation(project(":core:coroutines"))
                implementation(project(":core:navigation"))

                implementation(project(":library:compose-back"))
                implementation(project(":library:compose-adaptive"))
                implementation(project(":library:compose-navigation-suite"))
                implementation(project(":library:koin"))
                implementation(project(":library:paging3"))

                implementation(compose.material3)

                implementation(libs.findLibrary("paging-compose").get())
                implementation(libs.findLibrary("lifecycle-runtime-compose").get())
                implementation(libs.findLibrary("navigation-compose").get())

                implementation(libs.findLibrary("immutable").get())
                implementation(libs.findLibrary("shimmer").get())
                implementation(libs.findLibrary("material3-windowsize").get())
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
            }
        }

        val androidJvmMain = create("androidJvmMain")

        androidJvmMain.dependsOn(commonMain.get())
        androidMain.get().dependsOn(androidJvmMain)
        jvmMain.get().dependsOn(androidJvmMain)
    }
}
