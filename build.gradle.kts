plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("com.fazecast:jSerialComm:[2.0.0,3.0.0)")
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}


tasks {
    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        archiveBaseName.set("ArduinoKt")
        archiveVersion.set("1.0")
        configurations = listOf(project.configurations.getByName("compileClasspath"))
        project.setProperty("mainClassName", "org.lmdeveloper.arduino.bukkit.ArduinoKt")
    }
}