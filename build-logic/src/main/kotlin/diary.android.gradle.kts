import ext.android

android {
    compileSdk = Build.ANDROID_COMPILE_SDK
    defaultConfig {
        minSdk = Build.ANDROID_MIN_SDK
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
