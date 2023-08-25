plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("io.realm.kotlin")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.myhouse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myhouse"
        minSdk = 24
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundationLayout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.iconExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.toolingPreview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.viewModel)
    implementation(libs.androidx.compose.ui.utils)
    implementation(libs.androidx.compose.uiTestManifest)
    implementation(libs.androidx.compose.uiTest)
    implementation(libs.androidx.compose.lifecycl.runtime)

    //Debug
    debugImplementation(libs.androidx.compose.tooling)
    debugImplementation(libs.androidx.compose.toolingPreview)

    //Test
    androidTestImplementation(composeBom)

    //ImageLoader
    implementation(libs.coil.compose)

    //System UI Controller
    implementation(libs.google.accompanist.systemUiController)

    //Hilt
    implementation(libs.google.hilt.android)
    implementation(libs.androidx.startup)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.google.hilt.compiler)
    kapt(libs.google.hilt.android.compiler)
    kaptAndroidTest(libs.google.hilt.compiler)

    //Realm
    implementation(libs.realm.base)
    implementation(libs.realm.sync)

    //Ktor
    implementation(libs.ktor.android)
    implementation(libs.ktor.core)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.serialization)

    //ImmutableCollections
    implementation(libs.jetbrains.collections.immutable)

    //Coroutine
    implementation(libs.kotlin.coroutines)
}