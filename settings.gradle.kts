pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com.android.*")
                includeGroupByRegex("com.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            content {
                includeGroupByRegex("org.jetbrains.compose.*")
                includeGroupByRegex("org.jetbrains.androidx.*")
                includeGroupByRegex("org.jetbrains.skiko.*")
            }
        }
    }

    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com.android.*")
                includeGroupByRegex("com.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            content {
                includeGroupByRegex("org.jetbrains.compose.*")
                includeGroupByRegex("org.jetbrains.androidx.*")
                includeGroupByRegex("org.jetbrains.skiko.*")
            }
        }
    }
}

rootProject.name = "Diary"

include(":core:compose")
include(":core:coroutines")
include(":core:datetime")
include(":core:api-holiday")
include(":core:database-diary")
include(":core:database-holiday")
include(":core:model")
include(":core:navigation")
include(":core:pref-holiday")
include(":core:test")

include(":data:memo")
include(":data:memo-tag")
include(":data:tag")
include(":data:holiday")

include(":app:common")
include(":app:android")
include(":app:ios")
include(":app:jvm")

include(":domain:core")
include(":domain:account")
include(":domain:memo")
include(":domain:memo-tag")
include(":domain:tag")
include(":domain:holiday")

include(":feature:memo")
include(":feature:tag")
include(":feature:calendar")

include(":library:compose-adaptive")
include(":library:compose-navigation-suite")
include(":library:compose-back")
include(":library:koin")
include(":library:paging3")
include(":library:uuid")
