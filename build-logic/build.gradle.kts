plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

dependencies {
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.android)
    implementation(libs.gradle.compose)
    implementation(libs.gradle.compose.compiler)
}

gradlePlugin {
    plugins {
        register("diary.kotest") {
            id = "diary.kotest"
            implementationClass = "plugin.DiaryKotestPlugin"
        }
    }
}