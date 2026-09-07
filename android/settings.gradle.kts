pluginManagement {
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

rootProject.name = "OfficeAndroid"
include(":app")
include(":core:common")
include(":core:ui")
include(":core:filesystem")
include(":domain")
include(":data")
include(":document-engine:engine-api")
include(":document-engine:pdf-engine")
include(":document-engine:libreoffice-engine")
