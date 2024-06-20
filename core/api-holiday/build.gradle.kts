plugins {
    id("diary.module.common")
    id("diary.koin")
    id("diary.kotest")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:model"))
                implementation(libs.ktor.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.json)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        commonTest {
            dependencies {
                implementation(project(":core:test"))
                implementation(libs.ktor.mock)
            }
        }
    }
}
