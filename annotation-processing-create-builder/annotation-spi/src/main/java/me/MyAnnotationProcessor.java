package me;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * MyClass 자바 파일 생성 후 컴파일 처리
 * 컴파일 시 annotation의 value와 count 값을 출력
 */
@SupportedAnnotationTypes("me.MyCustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement annotation: annotations) {
            for(Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                MyCustomAnnotation myCustomAnnotation = element.getAnnotation(MyCustomAnnotation.class);
                String value = myCustomAnnotation.value();
                int count = myCustomAnnotation.count();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "value: " + value + ", count: " + count);
                System.out.println("value: " + value + ", count: " + count);
            }
        }
        return true;
    }
}

