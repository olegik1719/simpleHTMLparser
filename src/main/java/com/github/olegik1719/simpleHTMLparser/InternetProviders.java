package com.github.olegik1719.simpleHTMLparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

public class InternetProviders {
    private final static String folder_resourses = "src/main/resourses/";
    private static String url;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(folder_resourses+"properties.ini")));
        } catch (IOException e){
            e.printStackTrace();
        }
        url = properties.getProperty("url");
    }


    public static void getProviders() throws Exception {
        //module-1-13-6-1-1-2;
        //String url =
        String mkd = folder_resourses + "MKD.lst";
        String urls = folder_resourses+"url.lst";
        String just_url = folder_resourses+"url_just.lst";
        String error = "Точных совпадений нет";
        String error_log = folder_resourses+"errors.lst";

        try (FileWriter fileWriter = new FileWriter(urls);
             FileWriter errorWriter = new FileWriter(error_log);
             FileWriter urlWriter = new FileWriter(just_url);
            BufferedReader bufferedReader
                    = new BufferedReader(new FileReader(mkd))) {
            String s = bufferedReader.readLine();
            int i=0;
            while (s != null) {
                System.out.printf("%s%n",i++);
                Document doc = getDoc(getSearchUrl(s));
                if (doc.text().contains(error)) {
                    doc = getDoc(getSearchUrl(removeLiter(s)));
                    if (doc.text().contains(error))
                        errorWriter.write(String.format("%s ; %s%n", s, removeLiter(s)));
                    else
                        fileWriter.write(String.format("%s:%n%s%n",removeLiter(s),getSearchUrl(removeLiter(s))));
                } else fileWriter.write(String.format("%s:%n%s%n",s,getSearchUrl(s)));
                if (! doc.text().contains(error)){
                    String elem = doc.getElementsByTag("h3").first().getElementsByTag("a").first().attr("href");
                    int askPos = elem.indexOf('?');
                    int slushPos = elem.lastIndexOf('/') + 1;
                    String id = askPos > 0 ? elem.substring(slushPos,askPos):elem.substring(slushPos);
                    String writeOut = String.format("%s%n%n", url + "/spb/geo/"
                            + id + "/service/" + id
                            + "/group/internet");
                    fileWriter.write(writeOut);
                    urlWriter.write(writeOut);

//                    System.out.printf("%s%n", url + "spb/service/"
//                            + doc.getElementsByTag("h3").first().getElementsByTag("a").first().attr("href").substring(9)
//                            + "/group/internet");
////                    fileWriter.write(String.format("%s:%n%s%n%n",s,getSearchUrl(s)));
////                    Element element = doc.getElementById("module-1-13");
////                    if (element != null){
////                        //System.out.printf("%s%n",element.getElementsByAttributeValue("class","link _undashed card__addressLink _name"));
////                        System.out.printf("%s%n",element);
////                    }
////                    else
////                        System.out.printf("%s%n",doc.text());
                }
                fileWriter.flush();
                errorWriter.flush();
                urlWriter.flush();
                if (i % 10 == 0) Thread.sleep(1000*10);
                s = bufferedReader.readLine();
            }
        }
    }

    private static Document getDoc(String URLsite) throws IOException, InterruptedException {
        Thread.sleep(1000*4);
        return Jsoup.connect(URLsite)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(1000*8) //it's in milliseconds, so this means 5 seconds.
                .ignoreHttpErrors(true)
                .get();
    }

    public static String getSearchUrl(String address) throws UnsupportedEncodingException {
        return url + "/spb/search/"+ URLEncoder.encode("Санкт-Петербург, "
                + address, "UTF-8").replace("+", "%20");// + "/";
    }

    public static String removeLiter(String address){
        return address.substring(0,address.lastIndexOf(", литера "));
    }
}

