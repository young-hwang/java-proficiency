import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class ExecutionPathTest {

    public static void main(String[] args) {
        // jar로 파일이 실행된 경우 실행 중인 jar 파일의 절대 경로
        File file = new File(
            ExecutionPathTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println("jar file path: " + file.getAbsoluteFile());
        System.out.println("jar file parent path: " + file.getParent());


        // Class가 로드된 경로
        String path = ExecutionPathTest.class.getResource("").getPath();
        System.out.println("class load path: " + path);

        // 현재 작업 디렉토리(실행 위치)
        String property = System.getProperty("java.class.path");
        System.out.println("class load property: " + property);

        String userDir = System.getProperty("user.dir");
        System.out.println("user.dir: " + userDir);

        // 클래스 패스 내 파일의 실제 경로
        // getClassLoader().getResource("fileName")을 사용시 jar 내부의 리소스 파일도 읽을 수 있음
        // URL resource = ExecutionPathTest.class.getClassLoader().getResource("config.properties");
        // if (resource != null) {
        //     System.out.println("리소스 파일 경로: " + Paths.get(resource.getPath()).toAbsolutePath());
        // } else {
        //     System.out.println("파일을 찾을 수 없음.");
        // }
    }
}
