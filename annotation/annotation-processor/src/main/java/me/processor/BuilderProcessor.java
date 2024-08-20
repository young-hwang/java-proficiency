package me.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// 이 프로세서가 처리할 annotation 지정
// Processor 인터페이스의 getSupportedAnnotationTypes 구현 or @SupportedAnnotationTypes 사용
// 구체적인 주석 클래스 이름뿐만 아니라 "com.baeldung.annotation.*" 와 같이 와일드카드를 지정하여 com.baeldung.annotation 패키지와
// 모든 하위 패키지 내의 주석을 처리 하거나 "*"를 사용하여 모든 주석을 처리할 수도 있다.
// 우리가 구현해야 할 단일 메서드는 처리 자체를 수행하는 프로세스 메서드이므로 일치하는 주석이 포함된 모든 소스 파일에 대해 컴파일러에서 호출된다.
// annotation은 첫 번째 Set<? extends TypeElement> 주석 인수로 전달되고 현재 처리 라운드에 대한 정보는 RoundEnviroment roundEnv 인수로 전달됩니다.
// annotation 프로세서가 전달된 모든 주석을 처리했고
// 해당 annotation이 목록 아래의 다른 주석 프로세서로 전달되지 않도록 하려는 경우 반환되는 부울 값은 true 여야 합니다 .
@SupportedAnnotationTypes("me.annotation.BuilderProperty")

// 지원되는 소스 코드 버전 지정
@SupportedSourceVersion(SourceVersion.RELEASE_8)

// Auto-service 프로세스 메타 데이터 생성 지원
@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
                    Collectors.partitioningBy(element ->
                            // Element.asType() 메소드를 사용하여 TypeMirror 클래스의 인스턴스를 받음
                            ((ExecutableType) element.asType()).getParameterTypes().size() == 1 && element.getSimpleName().toString().startsWith("set")
                    )
            );

            List<Element> setters = annotatedMethods.get(true);
            List<Element> otherMethods = annotatedMethods.get(false);

            // 사용자에게 잘못 annotation 된 메소드들에 대하여 경고해야 하므로
            // AbstractProcessor.processingEnv 보호 필드에서 액세스 할 수 있는  인스턴스를 사용한다.
            String className = ((TypeElement) setters.get(0)
                    .getEnclosingElement()).getQualifiedName().toString();
            Map<String, String> setterMap = setters.stream().collect(Collectors.toMap(
                    setter -> setter.getSimpleName().toString(),
                    setter -> ((ExecutableType) setter.asType())
                            .getParameterTypes().get(0).toString()
            ));

            try {
                writeBuilderFile(className, setterMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void writeBuilderFile(String className, Map<String, String> setterMap) throws IOException {
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName.substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(simpleClassName);
            out.print(" object = new ");
            out.print(simpleClassName);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(simpleClassName);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setterMap.entrySet().forEach(setter -> {
                String methodName = setter.getKey();
                String argumentType = setter.getValue();

                out.print("    public ");
                out.print(builderSimpleClassName);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");

        }
    }
}
