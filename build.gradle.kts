import java.io.FileReader
import java.util.*

plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
    signing

    id("com.gradleup.nmcp") version "0.0.8"
}

val _properties = Properties().apply {
    load(FileReader("gradle.private.properties"))
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
                name = "KWArgs for Kotlin"
                description = "A implementation of some features of python for kotlin."
                url = "https://github.com/pSUNSET/KWArgsForKotlin"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "LShiftLess"
                        name = "NoodleInWater"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/pSUNSET/KWArgsForKotlin.git"
                    developerConnection = "scm:git:ssh://github.com/pSUNSET/KWArgsForKotlin.git"
                    url = "https://github.com/pSUNSET/KWArgsForKotlin"
                }
            }
        }
    }

//    repositories {
//        maven {
//            url = uri(layout.buildDirectory.dir("repo"))
//        }
//    }
}

nmcp {
    // Run publications -> ./gradlew publishAllPublicationsToCentralPortal
    publish("mavenKt") {
        username = properties["mavenCentralUsername"] as String
        password = properties["mavenCentralPassword"] as String
        // publish manually from the portal
        //publicationType = "USER_MANAGED"
        // or if you want to publish automatically
        publicationType = "AUTOMATIC"
    }
}

signing {
    sign(publishing.publications["mavenKt"])
}