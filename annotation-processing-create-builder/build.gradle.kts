plugins {
    id("java")
}

group = "me"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    group = "me"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(project(":annotation-processing-create-builder:annotation-user"))
}
