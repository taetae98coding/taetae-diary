package ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal fun Project.kotlin(
    action: KotlinProjectExtension.() -> Unit,
) {
    action(extensions.getByType())
}

internal fun Project.kotlinMultiplatform(
    action: KotlinMultiplatformExtension.() -> Unit,
) {
    action(extensions.getByType())
}