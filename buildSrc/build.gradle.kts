plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(libs.jreleaser.gradle.plugin)
}
