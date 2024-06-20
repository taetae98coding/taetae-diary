import ext.ifAndroid
import ext.kotlin

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("androidx.compose.foundation.ExperimentalFoundationApi")
        languageSettings.optIn("androidx.compose.foundation.layout.ExperimentalLayoutApi")
        languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
        languageSettings.optIn("androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi")
        languageSettings.optIn("androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi")
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    stabilityConfigurationFile.assign(rootProject.file("compose-stability-configuration-file.txt"))
}

ifAndroid {
    buildFeatures {
        compose = true
    }
}
