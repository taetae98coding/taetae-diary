import ext.kotlinMultiplatform
import ext.libs

plugins {
    id("diary.ksp")
}

kotlinMultiplatform {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-core").get())
                implementation(project.dependencies.platform(libs.findLibrary("koin-annotations-bom").get()))
                implementation(libs.findLibrary("koin-annotations").get())
            }
        }

        commonTest {
            dependencies {
                implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-test").get())
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", (platform(libs.findLibrary("koin-annotations-bom").get())))
    add("kspCommonMainMetadata", (libs.findLibrary("koin-compiler").get()))
}