import androidx.room.gradle.RoomExtension
import convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.room")
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                add("api", libs.findLibrary("room-ktx").get())
                add("implementation", libs.findLibrary("room-runtime").get())
                add("ksp", libs.findLibrary("room-compiler").get())
            }
        }
    }
}