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

include(
    ":feature:marketsummary",
    ":feature:search"
)

include(
    ":libs:design-system",
    ":libs:network",
)
