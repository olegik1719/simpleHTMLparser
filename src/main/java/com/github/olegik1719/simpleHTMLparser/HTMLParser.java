package com.github.olegik1719.simpleHTMLparser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * http://info.javarush.ru/translation/2014/12/21/3-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D0%B0-%D0%BA%D0%B0%D0%BA-%D1%80%D0%B0%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D1%82%D1%8C-HTML-%D1%84%D0%B0%D0%B9%D0%BB-%D0%B2-Java-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D1%83%D1%8F-Jsoup-.html
 * Java Program to parse/read HTML documents from File using Jsoup library.
 * Jsoup is an open source library which allows Java developer to parse HTML
 * files and extract elements, manipulate data, change style using DOM, CSS and
 * JQuery like method.
 *
 * @author Javin Paul
 */
public class HTMLParser {

    public static void main(String args[]) throws Exception {
        Properties properties = new Properties() {
            public Properties load(String path) {
                try (InputStream resourceAsStream =
                             getClass().getResourceAsStream(path)) {
                    load(resourceAsStream);
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                }
                return this;
            }
        }.load("resourses/properties.ini");
        String HTMLSTring = new String(Files.readAllBytes(Paths.get(properties.getProperty("html"))), StandardCharsets.UTF_8);
        Pattern p = Pattern.compile("/spb/firm/\\d*");
        Matcher m = p.matcher(HTMLSTring);
        try (FileWriter fileWriter = new FileWriter(properties.getProperty("csv"))) {
            while (m.find()) {
                String clAddr = "" + HTMLSTring.substring(m.start(), m.end());
                Document client = Jsoup.connect(clAddr).get();//parse(getHTML(clAddr));
                //System.out.printf("%s%n",getContacts(client));
                fileWriter.append(String.format("%s%n", getContacts(client)));
            }
        }


    }

    public static String getContacts(Document htmlFile) {
        String title = htmlFile.body().getElementsByTag("h1").text() + ";";
        Element div = null;
        Elements div2 = null;
        try {
            div = htmlFile.getElementById("module-1-13-1-1-1");
            //title = title + div.text();
            //title = title + div.getElementsByTag("bdo").text()+"\n";
            div2 = div.select("div.contact__phonesVisible");
            for (Element element : div2) {
                title = title + element.getElementsByTag("bdo").text() + ";";
            }
            //title = title + div.text();
        } catch (Exception e) {
            //System.out.printf("%s%n",title);
            //System.out.printf("%s%n", "Exception!");
            title += "Не удалось просканировать номера";
        }
        return title;
    }
}

