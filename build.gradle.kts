plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.junit5) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dokka.javadoc) apply false
    alias(libs.plugins.changelog)
}

allprojects {
    val projectVersion: String by project
    val projectVersionSuffix: String by project

    version = if (projectVersionSuffix.isBlank()) projectVersion else "$projectVersion-$projectVersionSuffix"
}

subprojects {
    group = "dev.slav.composemd"
    description = "${rootProject.name} ${project.name.replace('-', ' ')}"
}
