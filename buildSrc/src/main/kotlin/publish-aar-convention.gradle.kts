import org.jreleaser.model.Active
import org.jreleaser.model.api.deploy.maven.MavenCentralMavenDeployer

plugins {
    `maven-publish`
    id("org.jreleaser")
}

publishing {
    publications {
        register<MavenPublication>("libraryAar") {
            afterEvaluate {
                from(project.components["release"])
                artifact(project.tasks["javadocJar"])
            }

            artifactId = project.name
            groupId = "${project.group}"
            version = "${project.version}"

            val gitUrl = "https://github.com/slav-dev/compose-md"

            pom {
                name.set("${rootProject.name} ${project.name.replace('-', ' ')}")
                description.set("Composable Markdown library for Android")
                url.set(gitUrl)
                scm {
                    connection.set("scm:git:$gitUrl.git")
                    developerConnection.set("scm:git:$gitUrl.git")
                    url.set(gitUrl)
                }
                licenses {
                    license {
                        name.set("BSD 3-Clause License")
                        url.set("https://opensource.org/license/bsd-3-clause")
                    }
                }
                developers {
                    developer {
                        id.set("sczerwinski")
                        name.set("Slawomir Czerwinski")
                        email.set("slawomir@czerwinski.it")
                        url.set("https://slav.dev/")
                    }
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("$gitUrl/issues")
                }
                ciManagement {
                    system.set("GitHub Actions")
                    url.set("$gitUrl/actions")
                }
            }
        }
    }
    repositories {
        maven {
            name = "staging-deploy"
            url = project.uri(project.layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

jreleaser {
    gitRootSearch.set(true)
    signing {
        active.set(Active.ALWAYS)
        armored.set(true)
    }
    deploy {
        maven {
            active.set(Active.ALWAYS)
            nexus2 {
                create("snapshots") {
                    active.set(Active.SNAPSHOT)
                    url.set("https://central.sonatype.com/repository/maven-snapshots/")
                    sign.set(true)
                    verifyPom.set(false)
                    snapshotSupported.set(true)
                    stagingRepository("build/staging-deploy")
                    verifyUrl.set("https://central.sonatype.com/service/rest/repository/browse/maven-snapshots/{{path}}/{{filename}}")
                    snapshotUrl.set("https://central.sonatype.com/repository/maven-snapshots/")
                    closeRepository.set(true)
                    releaseRepository.set(true)
                }
            }
            mavenCentral {
                create("sonatype") {
                    active.set(Active.RELEASE)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    verifyPom.set(false)
                    stagingRepository("build/staging-deploy")
                    stage.set(MavenCentralMavenDeployer.Stage.UPLOAD)
                    retryDelay.set(10)
                    maxRetries.set(180)
                }
            }
        }
    }
    release {
        github {
            username.set("sczerwinski")
            skipTag.set(true)
            skipRelease.set(true)
        }
    }
}
