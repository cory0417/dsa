plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("run") {
    classpath = project.sourceSets.getByName("main").runtimeClasspath
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(11)
}