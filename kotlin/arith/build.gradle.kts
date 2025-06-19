plugins {
    kotlin("jvm") version "2.1.20"
    id("org.javacc.javacc") version "4.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}

project.tasks["compileKotlin"].dependsOn("compileJavacc")

tasks {
    compileJavacc {
        outputDirectory = project.layout.buildDirectory.dir("generated/javacc/main/java/arith/parser").get().asFile
    }
}

sourceSets.main {
    kotlin.srcDir(project.layout.buildDirectory.dir("generated/javacc/main/java").get().asFile)
}
