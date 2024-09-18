import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.superjob.android.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "superjob.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidFeature") {
            id = "superjob.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidLibrary") {
            id = "superjob.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidRoom") {
            id = "superjob.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidDagger") {
            id = "superjob.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }
    }
}