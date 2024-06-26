plugins {
    id("diary.module.feature")
    id("diary.koin")
    id("diary.room")
}

android {
    namespace = "${Build.NAMESPACE}.core.database.diary"
}
