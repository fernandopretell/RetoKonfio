plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.kotlinSerialization) apply false
    alias (libs.plugins.hilt.application) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

}