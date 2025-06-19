plugins {
    java
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

dependencies {
    testImplementation(kotlin("test"))
    implementation(files(tasks.compileJavacc.get().outputDirectory))
}

sourceSets {
    main {
        java {
            srcDirs.add(tasks.compileJavacc.get().outputDirectory)
        }
    }
}