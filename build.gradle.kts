plugins {
    id("java")
}

group = "me"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

allprojects {
    apply(plugin = "java")

    group = "me"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.slf4j:slf4j-api:1.7.36")
        // https://mvnrepository.com/artifact/ch.qos.logback/logback-core
        implementation("ch.qos.logback:logback-core:1.4.11")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}
