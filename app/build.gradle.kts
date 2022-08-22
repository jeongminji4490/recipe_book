import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.sharerecipy"
        minSdk = Apps.MIN_SDK
        targetSdk = Apps.TARGET_SDK
        versionCode = Apps.VERSION_CODE
        versionName = Apps.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "RECIPE_API_KEY", getApiKey("api.key"))
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
        compose=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
        kotlinCompilerVersion = "1.6.0"
    }
}

dependencies {

    implementation(KTX.CORE)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.MATERIAL)
    implementation(AndroidX.CONSTRAINT_LAYOUT)

    testImplementation(Test.JUNIT)
    androidTestImplementation(AndroidTest.EXT_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    //Navigation
    implementation(NavComponent.NAVIGATION_FRAGMENT)
    implementation(NavComponent.NAVIGATION_UI)
    implementation(NavComponent.NAVIGATION_DYNAMIC_FEATURES_FRAGMENT)
    implementation(NavComponent.NAVIGATION_TESTING)
    implementation(NavComponent.NAVIGATION_COMPOSE)

    // glide
    implementation(Glide.GLIDE)

    // compose
    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_TOOLING_UI)
    implementation(Compose.COMPOSE_FOUNDATION)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_ICONS_CORE)
    implementation(Compose.COMPOSE_ICONS_EXTENDED)
    implementation(Compose.COMPOSE_LIVEDATA)
    implementation(Compose.COMPOSE_UI_TEST)

    // Firebase
    implementation(Firebase.FIREBASE_ANALYTICS)
    implementation(Firebase.FIREBASE_UI_AUTH)
    implementation(Firebase.FIREBASE_AUTH)
    implementation(Firebase.FIREBASE_FIRESTORE)

    // Google
    implementation(Google.GOOGLE_SERVICE)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.RETROFIT_CONVERTER)

    // Dagger Hilt
    implementation(Hilt.HILT)
    kapt(Hilt.HILT_GOOGLE_COMPILER)
    kapt(Hilt.HILT_COMPILER)
    implementation(Hilt.HILT_NAVIGATION_COMPOSE)

    // DataStore
    implementation(DataStore.DATASTORE)
    implementation(DataStore.DATASTORE_CORE)
}

kapt {
    correctErrorTypes = true
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}
