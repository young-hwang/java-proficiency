package me;

import org.junit.jupiter.api.Test;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyAnnotationProcessorTest {
    @Test
    public void testAnnotationProcessing() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 컴파일할 소스 코드 작성
        String source = "package me;\n" +
                        "@MyCustomAnnotation(value = \"My Custom\", count = 30)\n" +
                        "public class MyClazz {}\n";
        JavaFileObject file = new SimpleJavaFileObject(URI.create("string:///MyClazz.java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };

        // 컴파일 작업 설정
        JavaCompiler.CompilationTask task = compiler.getTask(
                null, fileManager, diagnostics, null, null, Collections.singletonList(file));
        task.setProcessors(Collections.singletonList(new MyAnnotationProcessor()));

        // 컴파일 수행
        boolean success = task.call();

        // 컴파일 결과 확인
        assertTrue(success, "Compilation failed");
        assertTrue(diagnostics.getDiagnostics().stream()
                .anyMatch(d -> d.getMessage(null).contains("value: My Custom, count: 30")),
                "value: My Custom");
    }
}
