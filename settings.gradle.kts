pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    includeBuild("logic")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SuperJob"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":feature:favorites")
include(":feature:home")
include(":feature:login")
include(":feature:messages")
include(":feature:profile")
include(":feature:reactions")
include(":feature:vacancy")