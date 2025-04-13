# Compose.md

Composable Markdown library for Android.

![Last Commit][badge-last-commit]

![Main Branch Build][badge-main-status]

## Supported Features

Currently, the library supports:

- text paragraphs
- headings
- emphasis
- strong emphasis
- links (including link reference definition)
- hard line breaks
- bullet lists
- ordered lists
- block quotes
- code spans
- code blocks
- HTML (not recommended, limited Markdown support inside inline HTML)
- thematic breaks

## Gradle Configuration

Define the dependency in your `gradle/libs.versions.toml`:

```toml
[versions]
composeMd = "LATEST_VERSION"

[libraries]
composemd-core = { group = "dev.slav.composemd", name = "core", version.ref = "composeMd" }
```

Use the dependency in your app’s `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.composemd.core)
}
```

Alternatively, if your project is not using TOML:

```kotlin
dependencies {
    implementation("dev.slav.composemd:core:LATEST_VERSION")
}
```

### Snapshots

![Maven Snapshot][badge-snapshot]

To use snapshots, add the following repository in your `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven {
            name = "Maven Central Snapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
    }
}
```

To always use the latest snapshot, add the following lines to your app’s `build.gradle.kts`:

```kotlin
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}
```

## Basic Usage

To use Material Design typography, add `MdConfig` to your theme:

```kotlin
MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography
) {
    MdConfig(
        content = content
    )
}
```

### Single Paragraph

To render a single paragraph of text, use `MdText`:

```kotlin
MdText(
    markdown = markdownText,
    modifier = Modifier.fillMaxWidth()
)
```

### Column

To render a more complex Markdown text in a column, use `MdColumn`:

```kotlin
MdColumn(
    markdown = markdownText,
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
)
```

### Lazy Column

To render a long Markdown document in a lazy column, use `MdLazyColumn`:

```kotlin
MdLazyColumn(
    markdown = markdownText,
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
)
```

## Licenses

![BSD-3-Clause License][badge-license]

### 3rd Party Licenses

Compose.md uses 3rd party libraries that may be licensed differently.

- [commonmark-java]: ![BSD-2-Clause License][commonmark-java-license]


[badge-last-commit]: https://img.shields.io/github/last-commit/slav-dev/compose-md/main?style=flat
[badge-main-status]: https://img.shields.io/github/check-runs/slav-dev/compose-md/main?style=flat&label=main
[badge-snapshot]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fdev%2Fslav%2Fcomposemd%2Fcore%2Fmaven-metadata.xml&style=flat&label=snapshot
[badge-license]: https://img.shields.io/github/license/slav-dev/compose-md?style=flat

[commonmark-java]: https://github.com/commonmark/commonmark-java
[commonmark-java-license]: https://img.shields.io/github/license/commonmark/commonmark-java?style=flat
