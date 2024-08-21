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

    dependencies {
        // https://mvnrepository.com/artifact/com.google.auto.service/auto-service
        implementation("com.google.auto.service:auto-service:1.1.1")
    }

    tasks.test {
        useJUnitPlatform()
    }
}
