import ext.kotlinMultiplatform

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("diary.kotlin")
}

kotlinMultiplatform {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    applyDefaultHierarchyTemplate()
}