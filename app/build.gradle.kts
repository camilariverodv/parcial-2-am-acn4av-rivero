plugins {
    //alias(libs.plugins.android.application)
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.ebooking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ebooking"
        minSdk = 24
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
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation("com.google.android.gms:play-services-tasks:18.2.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore:24.8.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.tasks)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}