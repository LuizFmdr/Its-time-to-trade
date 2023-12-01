pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "timetotrade"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":app:menu-list")
include(":libs:design-system")
include(":feature:menu-list")
