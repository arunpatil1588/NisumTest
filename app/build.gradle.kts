plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.nisumtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nisumtest"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

        // Jetpack Compose
        implementation("androidx.compose.ui:ui:1.5.0")
        implementation ("androidx.compose.material:material:1.5.0")
        implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")

        // Navigation
        implementation ("androidx.navigation:navigation-compose:2.7.2")

        // Retrofit for networking
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

        // Hilt for Dependency Injection
        implementation ("com.google.dagger:hilt-android:2.48")
        kapt ("com.google.dagger:hilt-compiler:2.48")
    implementation (libs.androidx.hilt.navigation.compose)
    kapt (libs.androidx.hilt.compiler)

        // Lifecycle components
        implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
// paging
    implementation ("androidx.paging:paging-runtime-ktx:3.2.0")
    implementation ("androidx.paging:paging-compose:3.2.0")
        // Gson for JSON parsing
        implementation ("com.google.code.gson:gson:2.8.9")
    // Coil for image loading in Compose
    implementation ("io.coil-kt:coil-compose:2.5.0")
        // Testing
        testImplementation ("junit:junit:4.13.2")
        androidTestImplementation ("androidx.test.ext:junit:1.1.5")
        androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")


    // Coroutines testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Mockk for mocking dependencies
    testImplementation ("io.mockk:mockk:1.12.4")

    // Android Architecture Components testing
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    // Hilt testing (if needed)
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.40")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.40")


    }