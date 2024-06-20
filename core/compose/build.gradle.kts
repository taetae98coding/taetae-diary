plugins {
    id("diary.module.feature")
    id("diary.compose")
    id("diary.compose.test")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:model"))

                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                implementation(libs.immutable)
                implementation(libs.shimmer)
                implementation(libs.markdown)
                implementation(libs.paging.compose)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(project(":library:paging3"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.compose"
}
