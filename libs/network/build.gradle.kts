plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.hilt)
    alias(libs.plugins.timetotrade.android.moshi)
    alias(libs.plugins.secrets)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.retrofit.moshi.converter)
    api(libs.retrofit)
}
