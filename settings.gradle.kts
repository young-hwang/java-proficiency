rootProject.name = "java-proficiency"
include("java-se-8")
include("java8")
include("rx-java")
include("rx-java")
include("annotation-module")
include("annotation:annotation-processor")
findProject(":annotation:annotation-processor")?.name = "annotation-processor"
include("annotation:annotaion-client")
include("annotation:annotaion-client", "annotation:annotation-processor")
findProject(":annotation:annotaion-client")?.name = "annotation-client"
