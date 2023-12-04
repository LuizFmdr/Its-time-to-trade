plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.library.compose)
}

android {
    namespace = "br.com.timetotrade.design_system"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
}
