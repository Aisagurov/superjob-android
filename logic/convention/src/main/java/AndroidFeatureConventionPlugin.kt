import com.android.build.gradle.LibraryExtension
import convention.commonConfiguration
import convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                commonConfiguration(this)
                defaultConfig.targetSdk = 34
                buildFeatures.viewBinding = true
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", libs.findLibrary("androidx-navigation-fragment-ktx").get())
                add("implementation", libs.findLibrary("androidx-navigation-ui-ktx").get())
                add("implementation", libs.findLibrary("material").get())
            }
        }
    }
}