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
            }
        }
    }
}

rootProject.name = "Diary"

include(":core:compose")
include(":core:coroutines")
include(":core:database-diary")
include(":core:model")
include(":core:navigation")

include(":data:memo")
include(":data:tag")

include(":app:common")
include(":app:android")
include(":app:ios")
include(":app:jvm")

include(":domain:core")
include(":domain:account")
include(":domain:memo")
include(":domain:tag")

include(":feature:memo")
include(":feature:tag")

include(":library:compose-adaptive")
include(":library:compose-navigation-suite")
include(":library:compose-back")
include(":library:koin")
include(":library:paging3")
include(":library:uuid")
