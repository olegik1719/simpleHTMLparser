package com.github.olegik1719.simpleHTMLparser;

import com.github.igorsuhorukov.phantomjs.PhantomJsDowloader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import com.github.igorsuhorukov.phantomjs.PhantomJsDowloader;
import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;

import java.util.logging.Level;

public class PhantomJSUsing {
    public static void getProviders() throws Exception {
        System.setProperty("phantomjs.binary.path", PhantomJsDowloader.getPhantomJsPath());
        System.out.printf("\"phantomjs.binary.path\":%s%n",System.getProperty("phantomjs.binary.path"));
        PhantomJSDriver phantomJSDriver = new PhantomJSDriver();

        phantomJSDriver.setLogLevel(Level.ALL);
        phantomJSDriver.get("");


        System.out.printf("!!!%s!!!%n",phantomJSDriver.getRemoteStatus());

        System.out.printf("%s%n",phantomJSDriver.getCurrentUrl());

        //PhantomJSDriver phantom = (PhantomJSDriver)d;

        // Do we get results back?
    }
}
