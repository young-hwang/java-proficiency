plugins {
    id("java")
}

group = "me"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":projects:annotation-processing-create-builder:annotation-processing"))
    annotationProcessor(project(":projects:annotation-processing-create-builder:annotation-processing"))
}

tasks.test {
    useJUnitPlatform()
}

// 필요한 경우 추가 컴파일 옵션 설정
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Amapstruct.suppressGeneratorTimestamp=true")
}