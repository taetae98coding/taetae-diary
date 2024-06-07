import ext.kotlin

kotlin {
    explicitApi()
    jvmToolchain(Build.JDK_VERSION)

    sourceSets.all {
        languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
}
