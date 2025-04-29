plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pharmacie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pharmacie"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation ("com.intuit.ssp:ssp-android:1.0.6")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    //C'est le cœur de Retrofit, la bibliothèque principale.
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//C'est le convertisseur JSON ↔ Objet Java
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.core:core-splashscreen:1.0.1" )
    implementation ("androidx.appcompat:appcompat:1.6.1")
}


