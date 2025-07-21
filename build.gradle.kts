plugins {
    kotlin("jvm") version "2.1.20"
    `maven-publish`
}

group = "btpos.dsl.brigadier"
version = "1.0.0"

repositories {
    mavenCentral()
    maven(url="https://libraries.minecraft.net")
    maven(url="https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.mojang:brigadier:1.0.18")
    runtimeOnly("btpos.dsl.brigadier:BrigadierDSL:1.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}