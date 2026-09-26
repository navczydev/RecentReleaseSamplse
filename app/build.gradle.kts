plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.recentreleasesamplse"
    compileSdk = 37
//    compileSdkExtension = 1 // This unlocks the 37.1 APIs required by Compose
    compileSdkMinor = 1

//    compileSdkPreview = "CinnamonBun"

    defaultConfig {
        applicationId = "com.example.recentreleasesamplse"
        minSdk = 37
        targetSdk = 37
//        targetSdkPreview = "CinnamonBun"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
//    implementation("androidx.compose.foundation:foundation")
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.locationbutton.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    // For apps using Compose
    implementation("androidx.photopicker:photopicker-compose:1.0.0-alpha02")
    implementation("androidx.compose.foundation:foundation:1.13.0-alpha03")
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material.icons.extended)
//    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3.android)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // appState & Transform
    implementation(libs.androidx.appstate)
    implementation(libs.androidx.appstate.transform)
    implementation(libs.androidx.appstate.datastore)

    androidTestImplementation(libs.androidx.ui.test.junit4.accessibility)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}