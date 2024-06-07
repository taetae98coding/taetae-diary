
import ext.kotlinMultiplatform

plugins {
    id("diary.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("diary.kotlin")
}

kotlinMultiplatform {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()
    applyDefaultHierarchyTemplate()
}
