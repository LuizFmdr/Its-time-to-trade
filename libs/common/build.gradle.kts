plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.hilt)
}

android {
    namespace = "br.com.timetotrade.common"
}

dependencies {
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
