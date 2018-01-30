package com.github.olegik1719.simpleHTMLparser;

import java.io.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {

        //Properties properties = new Properties();
        try(BufferedReader bufferedReader
                    = new BufferedReader(new FileReader("src/main/resourses/MKD.lst"))){
            String s = bufferedReader.readLine();
            while (s != null){
                System.out.printf("%s%n!!!%n",s);
                s = bufferedReader.readLine();
            }
        }

        //File file = new File();
        //System.out.printf("%s%n", file.getAbsolutePath());
        //properties.load(is);


    }
}
