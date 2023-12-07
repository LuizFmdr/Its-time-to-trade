plugins {
    alias(libs.plugins.timetotrade.android.application)
    alias(libs.plugins.timetotrade.android.application.compose)
    alias(libs.plugins.timetotrade.android.hilt)
    alias(libs.plugins.secrets)
}

android {
    namespace = "br.com.timetotrade"

    defaultConfig {
        applicationId = "br.com.timetotrade"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    implementation(projects.feature.marketsummary)
    implementation(projects.libs.designSystem)
    implementation(projects.feature.search)

    implementation(libs.androidx.activity.compose)
    implementation(libs.retrofit)
    implementation(libs.coil)
    implementation(libs.timber)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.accompanist.systemui.controller)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.splashscreen)

    debugImplementation(libs.leakcanary)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.tooling)
    androidTestImplementation(libs.androidx.compose.ui.tooling.preview)
}
