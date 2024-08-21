rootProject.name = "java-proficiency"
include("java-se-8")
include("java8")
include("rx-java")
include("rx-java")
include("annotation-module")
include("annotation-processing-create-builder")
include("annotation-processing-create-builder:annotation-processing")
findProject(":annotation-processing-create-builder:annotation-processing")?.name = "annotation-processing"
include("annotation-processing-create-builder:annotation-user")
findProject(":annotation-processing-create-builder:annotation-user")?.name = "annotation-user"
