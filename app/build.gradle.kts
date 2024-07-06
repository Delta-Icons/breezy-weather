plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "website.leifs.delta.breezyweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "website.leifs.delta.breezyweather"
        minSdk = 11
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        named("release") {
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = false
            isCrunchPngs = false // No need to do that, we already optimized them
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = false
        buildConfig = false

        // Disable some unused things
        aidl = false
        renderScript = false
        shaders = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {}