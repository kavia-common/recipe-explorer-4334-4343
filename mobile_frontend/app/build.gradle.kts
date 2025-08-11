plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.compose") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.compose") // REQUIRED for Compose on Kotlin 2.0+
    id("kotlin-kapt")
}

android {
    namespace = "org.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.example.app"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    // Enable core library desugaring for required dependencies (Java 8+ support)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // No manual composeOptions block or deprecated compose compiler version declaration
}

dependencies {
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation(project(":utilities"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("org.jetbrains.compose.ui:ui:1.6.10")
    implementation("org.jetbrains.compose.material3:material3:1.6.10")
    implementation("org.jetbrains.compose.material:material:1.6.10")
    implementation("org.jetbrains.compose.runtime:runtime:1.6.10")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    // Required for Java 8+ API desugaring (for :utilities and :list)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}


