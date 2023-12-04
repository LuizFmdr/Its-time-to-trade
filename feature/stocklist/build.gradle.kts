plugins {
    alias(libs.plugins.timetotrade.android.library)
    alias(libs.plugins.timetotrade.android.library.compose)
    alias(libs.plugins.timetotrade.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "br.com.timetotrade.feature.menulist"
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.designSystem)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    debugImplementation(libs.androidx.ui.tooling)
}
