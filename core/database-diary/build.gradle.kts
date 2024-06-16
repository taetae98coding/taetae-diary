plugins {
    id("diary.module.feature")
    id("diary.koin")
    id("diary.ksp")
    alias(libs.plugins.androidx.room)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.sqlite.bundled)

                api(project(":core:model"))
                api(libs.room.runtime)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.room.testing)
                implementation(libs.robolectric)
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.database.diary"

    sourceSets.getByName("test").assets.srcDirs("$projectDir/schemas")

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    kspAndroid(libs.room.compiler)
    kspJvm(libs.room.compiler)
}