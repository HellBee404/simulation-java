plugins {
    id("java")
    id("application")
}

group = "by.hellbee"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("by.hellbee.Main")
}

dependencies {
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}