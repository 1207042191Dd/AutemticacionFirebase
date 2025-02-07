plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.autemticacionfirebase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.autemticacionfirebase"
        minSdk = 25
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

    implementation("com.google.firebase:firebase-auth:21.0.1")
    implementation("com.google.firebase:firebase-firestore:24.0.0")
    implementation("com.google.firebase:firebase-storage:20.0.0")
    implementation("com.google.android.gms:play-services-auth:20.1.0")

    implementation("com.microsoft.identity.client:msal:5.0.0") {
        exclude(group = "io.opentelemetry")
    }

    implementation(platform("io.opentelemetry:opentelemetry-bom:1.18.0"))
    implementation("io.opentelemetry:opentelemetry-api:1.18.0")
    implementation("io.opentelemetry:opentelemetry-context:1.18.0")

    implementation("com.android.volley:volley:1.2.1")
    implementation("com.github.bumptech.glide:glide:4.12.0") // Para cargar imágenes
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
}
