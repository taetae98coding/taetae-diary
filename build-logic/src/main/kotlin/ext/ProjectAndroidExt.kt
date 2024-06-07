package ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

internal fun Project.ifAndroid(
    action: CommonExtension<*, *, *, *, *, *>.() -> Unit,
) {
    val library = extensions.findByType<LibraryExtension>()
    val application = extensions.findByType<ApplicationExtension>()

    library?.let(action)
    application?.let(action)
}

internal fun Project.android(
    action: CommonExtension<*, *, *, *, *, *>.() -> Unit,
) {
    val library = extensions.findByType<LibraryExtension>()
    val application = extensions.findByType<ApplicationExtension>()

    check(library != null || application != null)

    library?.let(action)
    application?.let(action)
}

internal fun Project.androidApplication(
    action: ApplicationExtension.() -> Unit,
) {
    action(extensions.getByType())
}