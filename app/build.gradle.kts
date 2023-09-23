plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.keries"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.keries"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    implementation ("com.facebook.fresco:fresco:2.5.0")
    implementation ("io.coil-kt:coil:1.4.0")// Use the latest version available
    implementation ("com.nostra13.universalimageloader:universal-image-loader:1.9.5")
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation ("com.google.firebase:firebase-messaging-directboot:23.2.1")
    implementation ("com.google.firebase:firebase-firestore:24.7.1")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.0")
    implementation("androidx.core:core-ktx:1.10.1")
//    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("com.google.firebase:firebase-firestore:24.7.1")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("com.airbnb.android:lottie:6.1.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation ("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.4.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
}