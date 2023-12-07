plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.library.compose)
    alias(libs.plugins.timetotrade.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "br.com.timetotrade.feature.market.summary"
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.designSystem)
    implementation(projects.libs.common)
    implementation(libs.compose.material)
    implementation(libs.timber)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    debugImplementation(libs.androidx.ui.tooling)
}
