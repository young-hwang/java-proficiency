plugins {
    id("java")
}

group = "me"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.auto.service/auto-service
    implementation("com.google.auto.service:auto-service:1.1.1")
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
}

tasks.test {
    useJUnitPlatform()
}

// 필요한 경우 추가 컴파일 옵션 설정
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Amapstruct.suppressGeneratorTimestamp=true")
}