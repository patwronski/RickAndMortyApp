// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    kotlin("android") version "1.9.22" apply false
    kotlin("jvm") version "1.9.22" apply false

    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.diffplug.spotless") version "5.3.0"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false

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