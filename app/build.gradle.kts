plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("com.google.gms.google-services")
}

android {
    namespace = "com.sangmoki.chatting_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sangmoki.chatting_app"
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
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // firebase bom
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    // firebase analytics
    implementation("com.google.firebase:firebase-analytics")
    // firebase auth
    implementation("com.google.firebase:firebase-auth-ktx")
    // firebase firestore
    implementation("com.google.firebase:firebase-firestore")

    // groupie 라이브러리
    implementation("com.github.lisawray.groupie:groupie:2.10.0")
    implementation("com.github.lisawray.groupie:groupie-viewbinding:2.10.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}