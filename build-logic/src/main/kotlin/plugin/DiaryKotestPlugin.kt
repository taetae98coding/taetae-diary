package plugin

import ext.kotlinMultiplatform
import ext.libs
import ext.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

internal class DiaryKotestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("io.kotest.multiplatform")

        target.kotlinMultiplatform {
            sourceSets {
                commonTest {
                    dependencies {
                        implementation(target.libs.findBundle("kotest").get())
                    }
                }

                jvmTest {
                    dependencies {
                        implementation(kotlin("reflect"))
                        implementation(target.libs.findLibrary("kotest-junit").get())
                        implementation(target.libs.findLibrary("mockk").get())
                    }
                }
            }
        }

        target.tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }
}
