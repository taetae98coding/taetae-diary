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
                implementation(project(":data:memo-tag"))
                implementation(project(":data:tag"))
                implementation(project(":domain:account"))
                implementation(project(":domain:memo"))
                implementation(project(":domain:memo-tag"))
                implementation(project(":domain:tag"))
                implementation(project(":feature:memo"))
                implementation(project(":feature:tag"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.app"
}
