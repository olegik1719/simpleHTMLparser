package main.java.com.github.olegik1719.simpleHTMLparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/main/resourses/properties.ini");
        //File file = new File();
        //System.out.printf("%s%n", file.getAbsolutePath());
        properties.load(is);
        for (Object key: properties.keySet()) {
            System.out.printf("%s:%s%n",key, properties.get(key));
        }

    }
}
