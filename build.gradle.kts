plugins {
    kotlin("jvm") version "1.9.21"
}

group = "ru.razornd.advent"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}