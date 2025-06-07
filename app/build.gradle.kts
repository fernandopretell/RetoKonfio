plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
    //id("org.jetbrains.kotlin.kapt")
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

    //------------------------------

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

    ksp (libs.hilt.android.compiler)
    ksp (libs.androidx.hilt.compiler)

    implementation("androidx.datastore:datastore-preferences:1.1.3")
    //implementation("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.room:room-runtime:2.7.1")
    ksp ("androidx.room:room-compiler:2.7.1")
}