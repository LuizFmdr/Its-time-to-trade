import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.detekt)
}

buildscript {
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://dl.google.com/dl/android/maven2")
        }
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.android.plugin)
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    basePath = projectDir.absolutePath
    reportsDir = file("$projectDir/build/reports/detekt/")

    source.setFrom(
        files(
            "$projectDir/app/src",
            "$projectDir/feature/marketsummary/src",
        )
    )
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        basePath = projectDir.absolutePath
        reportsDir = file("$projectDir/build/reports/detekt/")
    }
}
