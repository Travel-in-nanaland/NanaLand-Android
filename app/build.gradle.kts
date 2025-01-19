import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.google.service)
    alias(libs.plugins.hilt)
    id(libs.plugins.parcelize.get().pluginId)
    alias(libs.plugins.kapt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose)
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
        versionCode = 45
        versionName = "1.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", getProperty("baseUrl"))
        buildConfigField("String", "KAKAO_KEY", getProperty("kakaoKey"))
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
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeCompiler {
        includeSourceInformation = true
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}

dependencies {

    implementation(libs.bundles.app)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.ui.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.tooling.preview)

    implementation(libs.appcompat)
    implementation(libs.bundles.lifecycle)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.bundles.retrofit)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.presentation)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.services)

    implementation(libs.bundles.paging)

    implementation(libs.datastore)

}

kapt {
    correctErrorTypes = true
}