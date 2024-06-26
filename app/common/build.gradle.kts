plugins {
    id("diary.feature")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:coroutines"))
                implementation(project(":core:datetime"))
                implementation(project(":core:database-diary"))
                implementation(project(":core:database-holiday"))
                implementation(project(":core:pref-holiday"))
                implementation(project(":core:api-holiday"))
                implementation(project(":data:memo"))
                implementation(project(":data:memo-tag"))
                implementation(project(":data:tag"))
                implementation(project(":data:holiday"))
                implementation(project(":domain:account"))
                implementation(project(":domain:memo"))
                implementation(project(":domain:memo-tag"))
                implementation(project(":domain:tag"))
                implementation(project(":domain:holiday"))
                implementation(project(":feature:memo"))
                implementation(project(":feature:tag"))
                implementation(project(":feature:calendar"))
            }
        }
    }
}

android {
    namespace = "${Build.NAMESPACE}.app"
}
