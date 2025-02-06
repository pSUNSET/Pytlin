import java.io.FileReader
import java.util.*

plugins {
    `java-library`
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

group = "io.github.psunset"
version = "1.0.0"

base {
    archivesName = "kwargs-for-kotlin"
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
            groupId = "io.github.psunset"
            artifactId = "kwargs-for-kotlin"

            from(components["java"])

            pom {
                name = "KWArgs For Kotlin"
                description = "A KWArgs implementation"
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


    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
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
    sign(publishing.publications["mavenKt"]) //It's after adding this specific line that I got the error of no configured signatory
}