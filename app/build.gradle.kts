plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android")
//    id("com.google.devtools.ksp")
//    kotlin("kapt") version "1.9.23"
}

android {
    namespace = "com.bimobelajar.mynoterev"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bimobelajar.mynoterev"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0")

    implementation ("androidx.room:room-runtime:2.4.0")
    kapt("groupId:artifactId:1.9.23")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    android {
        buildFeatures {
            dataBinding = true
        }
    }
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}