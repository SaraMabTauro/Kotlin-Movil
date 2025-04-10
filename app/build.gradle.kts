plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Habilitar kapt
    alias(libs.plugins.compose.compiler) // Aplica el plugin
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.habitos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.habitos"
        minSdk = 25
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"  // Coincide con la version de ComposeCompiler
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.datastore.preferences)

    implementation(libs.androidx.core.ktx) // Usa la versión más reciente
    implementation(libs.lifecycle.runtime.compose) // Usa la versión más reciente
    implementation(libs.androidx.activity.compose) // Usa la versión más reciente
    implementation(platform(libs.androidx.compose.bom)) // Usa la versión más reciente

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Security Crypto
    implementation(libs.security.crypto)

    // Firebase
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.messaging.ktx) // No necesitas versión explícita
    implementation(libs.firebase.bom.v33120)
    implementation(libs.com.google.firebase.firebase.messaging.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation("androidx.core:core-ktx:1.16.0")


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4) // Sin versión explícita
    debugImplementation(libs.androidx.ui.tooling) // Sin versión explícita
    debugImplementation(libs.androidx.ui.test.manifest) // Sin versión explícita

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Glide
    implementation(libs.github.glide)
    annotationProcessor(libs.glide.compiler)

    // Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.bom)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.androidx.material)

    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.androidx.recyclerview)
    implementation(libs.material3)



    //DataStore
    implementation(libs.datastore.preferences)

    implementation(libs.androidx.hilt.navigation.compose)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)


    implementation (libs.androidx.fragment.ktx)


    // Security Crypto
    implementation(libs.security.crypto)
    // Glide
    implementation(libs.github.glide)
    annotationProcessor(libs.glide.compiler)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)

    // UI
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.ui.graphics)
    implementation(libs.ui.tooling.preview)

    implementation (libs.okhttp)

    implementation(libs.androidx.navigation.compose)


}