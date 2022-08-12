
object AndroidX {
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
}

object Google {
    const val GOOGLE_SERVICE = "com.google.android.gms:play-services-auth:${Versions.GOOGLE_SERVICE}"
}

object Firebase {
    //const val FIREBASE_BOM = "com.google.firebase:firebase-bom:30.2.0"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_UI_AUTH =  "com.firebaseui:firebase-ui-auth:${Versions.FIREBASE_UI_AUTH}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore-ktx"
}

object NavComponent {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURES_FRAGMENT = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION}"
    const val NAVIGATION_TESTING = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
}

object Glide {
    const val GLIDE = "com.github.skydoves:landscapist-glide:${Versions.GLIDE}"
}

object Compose {
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_TOOLING_UI = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
    const val COMPOSE_ICONS_CORE = "androidx.compose.material:material-icons-core:${Versions.COMPOSE}"
    const val COMPOSE_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
    const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2}"
}

object Hilt {
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_GOOGLE_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPOSE}"
    const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_COMPOSE}"
}

object DataStore {
    const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
    const val DATASTORE_CORE = "androidx.datastore:datastore-preferences-core:${Versions.DATASTORE}"
}

object Test {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
}

object AndroidTest {
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    const val TEST_RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
