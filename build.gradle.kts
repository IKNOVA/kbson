import java.util.Date

val LIBRARY_VERSION_NAME = "0.0.1"
val GROUP_ID = "com.github.jershell"
val ARTIFACT_ID = rootProject.name
val BINTRAY_REPOSITORY = "generic"
val BINTRAY_ORGINIZATION = "jershell"

buildscript {
    repositories { jcenter() }
    val kotlin_version = "1.3.41"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
    }
}

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
}

apply(plugin="kotlinx-serialization")

tasks.bintrayUpload {
    dependsOn("publishToMavenLocal")
}

bintray {
    user = project.property("user_name").toString()
    key = project.property("apikey").toString()
    publish = true
    override = false

    pkg.apply {
        repo = BINTRAY_REPOSITORY
        name = ARTIFACT_ID
        userOrg = BINTRAY_ORGINIZATION
        desc = "This adapter adds BSON support to kotlinx.serialization.\n"
        setLicenses("MIT")
        version.apply {
            name = LIBRARY_VERSION_NAME
            vcsTag = LIBRARY_VERSION_NAME
            released = Date().toString()
        }

        vcsUrl = "https://github.com/jershell/kbson.git"
        websiteUrl = "https://github.com/jershell/kbson"
        issueTrackerUrl = "https://github.com/jershell/kbson/issues"

        setLabels("kotlin", "bson", "serialization", "jvm", "mongo", "mongodb", "kmongo")
    }
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mongodb:mongodb-driver-async:3.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.1")
    implementation("org.mongodb:mongodb-driver-sync:3.10.2")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")


    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
