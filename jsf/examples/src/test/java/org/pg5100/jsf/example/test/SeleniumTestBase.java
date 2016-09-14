package org.pg5100.jsf.example.test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public abstract class SeleniumTestBase {

    private static WebDriver driver;

    private static boolean tryToSetGeckoIfExists(Path path){
        if(Files.exists(path)){
            System.setProperty("webdriver.gecko.driver", path.toAbsolutePath().toString());
            return true;
        }
        return false;
    }

    private static WebDriver getFirefoxDriver(){
        /*
            Need to have an updated Firefox (eg 48.x), but also need
            to download and put the geckodriver (eg 0.10.0) in your own home dir.
            See:

            https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette/WebDriver
            https://github.com/mozilla/geckodriver/releases
         */

        String homeDir = System.getProperty("user.home");
        String executableName = "geckodriver";

        if(! tryToSetGeckoIfExists(Paths.get(homeDir,executableName))){
            if(! tryToSetGeckoIfExists(Paths.get(homeDir,executableName+".exe"))){
                fail("Cannot locate the "+executableName+" in your home directory "+homeDir);
            }
        }


        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability("marionette", true);
        desiredCapabilities.setJavascriptEnabled(true);

        return  new FirefoxDriver(desiredCapabilities);
    }

    @BeforeClass
    public static void init() throws InterruptedException {

        driver = getFirefoxDriver(); //need Selenium 3.x, but still giving few issues (at least on Mac)

        //TODO: need to configure Chrome

        /*
            When the integration tests in this class are run, it might be
            that WildFly is not ready yet, although it was started. So
            we need to wait till it is ready.
         */
        for(int i=0; i<30; i++){
            boolean ready = JBossUtil.isJBossUpAndRunning();
            if(!ready){
                Thread.sleep(1_000); //check every second
                continue;
            } else {
                break;
            }
        }
    }

    protected WebDriver getDriver(){
        return driver;
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }


    @Before
    public void startFromInitialPage(){

        //if for any reason WildFly is not running any more, do not fail the tests
        assumeTrue("Wildfly is not up and running", JBossUtil.isJBossUpAndRunning());
    }
}
