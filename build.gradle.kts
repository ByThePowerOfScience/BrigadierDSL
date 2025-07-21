plugins {
    kotlin("jvm") version "2.1.20"
}

group = "btpos.dsl.brigadier"
version = "1.0"

repositories {
    mavenCentral()
    maven(url="https://libraries.minecraft.net")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.mojang:brigadier:1.0.18")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}
