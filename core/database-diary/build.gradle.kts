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
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.database.diary"
}

room {
    schemaDirectory("$projectDir/scheme")
}

dependencies {
    kspAndroid(libs.room.compiler)
    kspJvm(libs.room.compiler)
}