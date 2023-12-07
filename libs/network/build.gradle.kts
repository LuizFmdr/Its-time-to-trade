plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.hilt)
    alias(libs.plugins.secrets)
    id("kotlinx-serialization")
}

android {
    namespace = "br.com.timetotrade.network"

    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    api(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
}
