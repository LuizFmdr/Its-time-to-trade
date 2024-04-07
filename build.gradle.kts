import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.doctor)
    alias(libs.plugins.room) apply false
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

doctor {
    allowBuildingAllAndroidAppsSimultaneously = true
    warnWhenNotUsingParallelGC = false
    javaHome {
        failOnError.set(false)
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    basePath = projectDir.absolutePath
    reportsDir = file("$projectDir/build/reports/detekt/")

/*    source.setFrom(
        files(
            "$projectDir/app/src",
            "$projectDir/feature/marketsummary/src",
        )
    )*/
}

tasks.register("detektAll", Detekt::class.java){
    val autoFix = project.hasProperty("detektAutoFix")

    description = "Custom DETEKT build for all modules"
    parallel = true
    ignoreFailures = true
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(file(projectDir))
    config.setFrom(file("${rootProject.projectDir}/config/detekt/detekt.yml"))
    include("**/*.kt")
    exclude("**/resources/**", "**/build/**")
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        reportsDir = file("$projectDir/build/reports/detekt/")
    }
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
