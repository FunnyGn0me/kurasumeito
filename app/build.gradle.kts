import java.io.FileInputStream
import java.util.Properties

android.buildFeatures.buildConfig = true

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = "com.example.kurasumeito"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kurasumeito"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val projectProperties = Properties()
        projectProperties.load(FileInputStream(rootProject.file("local.properties")))

        buildConfigField("String", "key", "\"${projectProperties["key"]}\"")
        buildConfigField("String", "secret", "\"${projectProperties["secret"]}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    //Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android.navigation)

    //Compose Material 3
    implementation(libs.androidx.compose.material3)

    // Room
    implementation (libs.androidx.room.runtime)
    ksp(libs.hilt.android.room.compiler)
    implementation (libs.androidx.room)

    //Navigation-serialization
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    //Coil
    implementation(libs.io.coil)
    implementation(libs.io.coil.network)

    //Media3 - Exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.exoplayer.ui)

    //Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}