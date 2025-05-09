import java.io.FileReader
import java.util.Properties

plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
    signing

    id("com.gradleup.nmcp") version "0.0.8"
}

val _properties = Properties().apply {
    load(FileReader("secrets.properties"))
}.toMutableMap()

for ((k, v) in _properties) {
    project.setProperty(k as String, v)
}

val repo_group_id       = project.property("group_id") as String
val repo_artifact_id    = project.property("artifact_id") as String
val repo_version        = project.property("version") as String

group = repo_group_id
version = repo_version

base {
    archivesName = repo_artifact_id
}

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("mavenKt") {
            groupId = repo_group_id
            artifactId = repo_artifact_id
            version = repo_version

            from(components["java"])

            pom {
                name = "Pytlin"
                description = "A library for implementing some features of python in kotlin."
                url = "https://github.com/pSUNSET/Pytlin"
                licenses {
                    license {
                        name = "The MIT License (MIT)"
                        url = "https://mit-license.org/"
                    }
                }
                developers {
                    developer {
                        id = "LShiftLess"
                        name = "NoodleInWater"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/pSUNSET/Pytlin.git"
                    developerConnection = "scm:git:ssh://github.com/pSUNSET/Pytlin.git"
                    url = "https://github.com/pSUNSET/Pytlin"
                }
            }
        }
    }
}

nmcp {
    // Run publications -> ./gradlew publishAllPublicationsToCentralPortal
    publish("mavenKt") {
        username = properties["mavenCentralUsername"] as String
        password = properties["mavenCentralPassword"] as String
        publicationType = "USER_MANAGED"
//        publicationType = "AUTOMATIC"
    }
}

signing {
    sign(publishing.publications["mavenKt"])
}