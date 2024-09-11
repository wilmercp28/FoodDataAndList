
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.9.0"
}



android {
    namespace = "com.wilmer.fooddataandlist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wilmer.fooddataandlist"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val keystoreFile = project.rootProject.file("ApiKey.properties")
        if (!keystoreFile.exists()) {
            // Create the file if it doesn't exist
            keystoreFile.createNewFile()
            // Write a default placeholder API key
            keystoreFile.writeText(
                """
        CLIENT_ID=your_api_key_here
        CLIENT_SECRET=your_api_key_here
        CONSUMER_KEY=your_api_key_here
        CONSUMER_SECRET=your_api_key_here
        """.trimIndent()
            )
        }
        val properties = Properties().apply {
            load(keystoreFile.inputStream())
        }

        val clientID = properties.getProperty("CLIENT_ID") ?: ""
        val clientSecret = properties.getProperty("CLIENT_SECRET") ?: ""
        val consumerKey = properties.getProperty("CONSUMER_KEY") ?: ""
        val consumerSecret = properties.getProperty("CONSUMER_SECRET") ?: ""

        buildConfigField("String", "CLIENT_ID", "\"$clientID\"")
        buildConfigField("String", "CLIENT_SECRET", "\"$clientSecret\"")
        buildConfigField("String", "CONSUMER_KEY", "\"$consumerKey\"")
        buildConfigField("String", "CONSUMER_SECRET", "\"$consumerSecret\"")


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
        compose = true
        buildConfig = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation (libs.kotlinx.serialization.json)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.kotlinx.coroutines.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")

    //navigation
    val nav_version = "2.7.7"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //timber
    implementation (libs.timber)

    //okhttp3
    implementation (libs.okhttp)

}

