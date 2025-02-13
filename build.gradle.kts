// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    kotlin("android") version "2.1.0" apply false
    kotlin("jvm") version "2.1.0" apply false
    kotlin("plugin.compose") version "2.1.0" apply false
    id ("com.google.dagger.hilt.android") version "2.53.1" apply false
    id("com.diffplug.spotless") version "5.3.0"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false

}



apply(plugin = "com.diffplug.spotless")
spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile(
            rootProject.file("${project.rootDir}/spotless/LICENSE.txt"),
            "^(package|object|import|interface)"
        )
    }
}