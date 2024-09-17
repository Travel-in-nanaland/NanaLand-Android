import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.jeju.nanaland"
    compileSdk = 34

    // 설정하지 않으면, 플레이스토어에 배포할 때 리소스 관련(언어별 string) 문제가 발생할 수 있다.
    bundle {
        language {
            enableSplit = false
        }
    }

    defaultConfig {
        applicationId = "com.jeju.nanaland"
        minSdk = 26
        targetSdk = 34
        versionCode = 33
        versionCode = 31
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", getProperty("baseUrl"))
//        buildConfigField("String", "NEXT_URL", getProperty("nextUrl"))
//        buildConfigField("String", "DEV_ACCESS_TOKEN", getProperty("accessToken"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            isShrinkResources = false
//            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.3")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.foundation:foundation:1.6.5")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose-android:2.8.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")

    // Retrofit - String Parser
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("com.google.dagger:hilt-android:2.37")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    // Navigation Safe Args
    implementation("androidx.navigation:navigation-compose:2.8.0-rc01")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.0")

    // Landscapist-Glide
    implementation("com.github.skydoves:landscapist-glide:2.2.12")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ViewPager
    implementation("androidx.viewpager:viewpager:1.1.0-alpha01")

    // Google Play services
    implementation("com.google.gms:google-services:4.3.15")
    implementation("com.google.firebase:firebase-auth:22.0.0")
    implementation("com.google.firebase:firebase-bom:32.0.0")
    implementation("com.google.android.gms:play-services-auth:20.5.0")

    // Kakao
    implementation("com.kakao.sdk:v2-user:2.20.1") // 카카오 로그인 API 모듈
    implementation("com.kakao.sdk:v2-share:2.20.1") // 카카오톡 공유 API 모듈

    // Lottie
    implementation("com.airbnb.android:lottie-compose:6.4.0")

    // Google 로그인
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")

    // paging
    implementation("androidx.paging:paging-runtime-ktx:3.3.1")
    implementation("androidx.paging:paging-compose:3.3.1")

    // drawable to painter
    implementation("com.google.accompanist:accompanist-drawablepainter:0.34.0")

    // Firebase Bom
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
}

kapt {
    correctErrorTypes = true
}