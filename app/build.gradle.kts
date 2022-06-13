plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")

}

android {
    val technicalVersion = 1
    val prodVersion = 1
    val stagingVersion = 0

    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.fizzbuzzapp"
        minSdk = 26
        targetSdk = 32
        versionCode = technicalVersion * 10000 + prodVersion * 100 + stagingVersion
        versionName = "$technicalVersion.$prodVersion.$stagingVersion"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    android.applicationVariants.all {
        val variant = this
        variant.outputs
            .map{ it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach {
                it.outputFileName = "fizzbuzzapp_${variant.buildType.name}_${defaultConfig.versionName}.apk"
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
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    // Core libraries
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Compose libraries
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-rc01")
    implementation("androidx.compose.compiler:compiler:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-viewbinding:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:${Versions.COMPOSE}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}")
    implementation("androidx.compose.material:material:${Versions.COMPOSE}")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Room
    implementation("androidx.room:room-runtime:${Versions.ROOM}")
    annotationProcessor("androidx.room:room-compiler:${Versions.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.ROOM}")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

kapt {
    correctErrorTypes = true
}
