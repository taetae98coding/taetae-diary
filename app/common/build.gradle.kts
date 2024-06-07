plugins {
    id("diary.feature")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:coroutines"))
                implementation(project(":core:database-diary"))
                implementation(project(":data:memo"))
                implementation(project(":domain:account"))
                implementation(project(":domain:memo"))
                implementation(project(":feature:memo"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.app"
}
