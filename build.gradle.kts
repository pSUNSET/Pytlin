plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
    id("com.gradleup.nmcp") version "0.0.8"
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
        create<MavenPublication>("mavenJava") {
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
                    connection = "scm:git:https://github.com/pSUNSET/KWArgsForKotlin.git"
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
    publishAllProjectsProbablyBreakingProjectIsolation {
        username = "tsl4Myo0"
        password = "e+FT31IzFjbc0qK7U0IBo39VZh4qB/LhoQX9tpdVuqOm"
        // publish manually from the portal
//        publicationType = "USER_MANAGED"
        // or if you want to publish automatically
        publicationType = "AUTOMATIC"
    }
}