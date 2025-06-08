plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hilt.application)
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.fernandopretell.retokonfio"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fernandopretell.retokonfio"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

kotlin {
    sourceSets.configureEach {
        kotlin.srcDir("build/generated/ksp/${name}/kotlin")
    }
}


dependencies {

    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation (libs.androidx.navigation.compose)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.tooling.preview.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation (libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation (libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation (libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.coil.compose)
    implementation(libs.retrofit)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    debugImplementation(libs.androidx.ui.tooling)

    ksp (libs.hilt.android.compiler)
    ksp (libs.androidx.hilt.compiler)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.datastore.preferences)
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(kotlin("test"))

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)


}