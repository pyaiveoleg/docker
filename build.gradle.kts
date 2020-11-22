import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("io.gitlab.arturbosch.detekt") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

application {
    mainClassName = "com.example.ApplicationKt"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val ktor_version = "1.4.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation ("io.ktor:ktor-server-netty:$ktor_version")
    implementation ("org.jsoup:jsoup:1.10.3")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.6.0")
}

detekt {
    failFast = true // fail build on any finding
    buildUponDefaultConfig = true // preconfigure defaults
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("application")
    archiveClassifier.set("")
    archiveVersion.set("")
}
