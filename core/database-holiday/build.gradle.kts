plugins {
    id("diary.module.feature")
    id("diary.koin")
    id("diary.room")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.datetime)
            }
        }

        commonTest {
            dependencies {
                implementation(project(":core:test"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.core.database.holiday"
}

dependencies {

}