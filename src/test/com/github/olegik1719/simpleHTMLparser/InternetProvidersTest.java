package com.github.olegik1719.simpleHTMLparser;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class InternetProvidersTest {

    private final static String folder_resourses = "src/main/resourses/";
    private static String url;
    private static String mkd = folder_resourses + "MKD.lst";
    private static String urls;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(folder_resourses+"properties.ini")));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        urls = properties.getProperty("outurl");
    }

    @Test
    public void getProviders() throws Exception {
        InternetProviders.getProviders();
    }

    @Test
    public void getSearchUrl() throws IOException {
        try (BufferedReader bufferedReader
                     = new BufferedReader(new FileReader(mkd));
            FileWriter fileWriter = new FileWriter(urls) ){
            String s = bufferedReader.readLine();
            while (s != null){
                //System.out.printf("%s%n!!!%n",s);
                //driver.get(url + "search/"+ URLEncoder.encode(s, Charsets.UTF_8.displayName()));
                //System.out.printf("%s%n",url + "search/"+ URLEncoder.encode(s, Charsets.UTF_8.displayName()));
                fileWriter.write(String.format("%s%n",InternetProviders.getSearchUrl(s)));
                fileWriter.write(String.format("%s%n",InternetProviders.getSearchUrl(InternetProviders.removeLiter(s))));
                s = bufferedReader.readLine();
            }
            //Projects projects = new Projects(baseUrl, driver);

        }
    }
}