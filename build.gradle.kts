plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.junit5) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dokka.javadoc) apply false
}

val projectVersion: String by project
val projectVersionSuffix: String by project

subprojects {
    group = "dev.slav.composemd"
    version = if (projectVersionSuffix.isBlank()) projectVersion else "$projectVersion-$projectVersionSuffix"
    description = "${rootProject.name} ${project.name.replace('-', ' ')}"
}
