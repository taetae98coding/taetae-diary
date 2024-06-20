import ext.android
import ext.kotlinMultiplatform
import ext.libs

plugins {
    id("diary.ksp")
    id("androidx.room")
}

kotlinMultiplatform {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.findLibrary("sqlite-bundled").get())

                api(project(":core:model"))
                api(libs.findLibrary("room-runtime").get())
            }
        }

        getByName("androidUnitTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.findLibrary("room-testing").get())
                implementation(libs.findLibrary("robolectric").get())
                implementation(libs.findLibrary("coroutines-test").get())
            }
        }
    }
}

android {
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
    add("kspAndroid", libs.findLibrary("room-compiler").get())
    add("kspIosX64", libs.findLibrary("room-compiler").get())
    add("kspIosArm64", libs.findLibrary("room-compiler").get())
    add("kspIosSimulatorArm64", libs.findLibrary("room-compiler").get())
    add("kspJvm", libs.findLibrary("room-compiler").get())
}