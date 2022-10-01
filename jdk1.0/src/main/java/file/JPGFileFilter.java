package file;

import java.io.File;
import java.io.FileFilter;

public class JPGFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.isFile()) {
            String name = pathname.getName();
            if(name.endsWith(".jpg")) {
                return true;
            }
        }
        return false;
    }
}
