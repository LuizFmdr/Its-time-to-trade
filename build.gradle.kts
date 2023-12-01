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
        classpath (libs.android.gradlePlugin)
        classpath (libs.kotlin.gradlePlugin)
        classpath (libs.kotlin.serialization)
        classpath (libs.hilt.android.plugin)
    }
}

plugins {
    alias(libs.plugins.ksp) apply false
}

