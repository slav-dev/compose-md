# Compose.md

Composable Markdown library for Android.

[![Last Commit][badge-last-commit]][composemd-commits]

[![Main Branch Build][badge-main-status]][composemd-actions]

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
- images (with `plugin-image`)

## Gradle Configuration

[![Maven Central Version][badge-version]][maven-central-search]

Define the dependency in your `gradle/libs.versions.toml`:

```toml
[versions]
composeMd = "LATEST_VERSION"

[libraries]
composemd-core = { group = "dev.slav.composemd", name = "core", version.ref = "composeMd" }

# And selected plugins:
composemd-plugin-image = { group = "dev.slav.composemd", name = "core", version.ref = "composeMd" }

[bundles]
composemd = ["composemd-core", "composemd-plugin-image"]
```

Use the dependency in your app’s `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.bundles.composemd)
}
```

Alternatively, if your project is not using TOML:

```kotlin
dependencies {
    implementation("dev.slav.composemd:core:LATEST_VERSION")

    // And selected plugins:
    implementation("dev.slav.composemd:plugin-image:LATEST_VERSION")
}
```

### Snapshots

[![Maven Snapshot][badge-snapshot]][maven-central-snapshots]

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

[![BSD-3-Clause License][badge-license]][composemd-license]

Compose.md is licensed under BSD-3-Clause License.

### 3rd Party Licenses

Compose.md uses 3rd party libraries that may be licensed differently.

| Dependency        |                                      License                                      |
|:------------------|:---------------------------------------------------------------------------------:|
| [commonmark-java] | [![BSD-2-Clause License][commonmark-java-badge-license]][commonmark-java-license] |


[composemd-commits]: https://github.com/slav-dev/compose-md/commits/main/
[composemd-actions]: https://github.com/slav-dev/compose-md/actions
[composemd-license]: https://github.com/slav-dev/compose-md/blob/main/LICENSE

[badge-last-commit]: https://img.shields.io/github/last-commit/slav-dev/compose-md/main?style=for-the-badge
[badge-main-status]: https://img.shields.io/github/check-runs/slav-dev/compose-md/main?style=for-the-badge&label=main
[badge-version]: https://img.shields.io/maven-central/v/dev.slav.composemd/core?style=for-the-badge
[badge-snapshot]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fdev%2Fslav%2Fcomposemd%2Fcore%2Fmaven-metadata.xml&style=for-the-badge&label=snapshot
[badge-license]: https://img.shields.io/github/license/slav-dev/compose-md?style=for-the-badge

[maven-central-search]: https://central.sonatype.com/search?namespace=dev.slav.composemd&sort=name
[maven-central-snapshots]: https://central.sonatype.com/service/rest/repository/browse/maven-snapshots/dev/slav/composemd/

[commonmark-java]: https://github.com/commonmark/commonmark-java
[commonmark-java-license]: https://github.com/commonmark/commonmark-java/blob/main/LICENSE.txt
[commonmark-java-badge-license]: https://img.shields.io/github/license/commonmark/commonmark-java?style=for-the-badge
