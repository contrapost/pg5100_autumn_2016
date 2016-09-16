package org.pg5100.jsf.examples.exp01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pg5100.jsf.examples.test.PageObject;

/**
 * See Page Object at:
 *
 * http://martinfowler.com/bliki/PageObject.html
 *
 */
public class CounterPageObject extends PageObject{


    public CounterPageObject(WebDriver driver) {
        super(driver);
    }

    public void toStartingPage(){
        getDriver().get(getBaseUrl()+"/ex01/ex01.jsf");
        waitForPageToLoad();

        //make sure we start from a 0 counter
        if(isOnPage() && ! Integer.valueOf(0).equals(getCounterValue())) {
            clickReset();
        }
    }

    /*
        We need a method to check if we are on the right page, eg to check we were
        not redirected to an error message page, or if we followed the wrong link
     */

    public boolean isOnPage(){
        return getDriver().getTitle().contains("simple counter");
    }

    /*
        When we can modify the HTML directly (eg access to source code), then
        the easiest approach for XPath is to give ids to each element we want
        to use.
        Note: some IDs might get modified by JSF, eg for forms, it concatenates
        the id of the form to the ids of the buttons / inside elements
     */

    public void clickPlus(){
        WebElement button = getDriver().findElement(By.id("form:plusButtonId"));
        button.click();
        waitForPageToLoad();
    }


    public void clickMinus(){
        WebElement button = getDriver().findElement(By.id("form:minusButtonId"));
        button.click();
        waitForPageToLoad();
    }

    public void clickReset(){
        WebElement button = getDriver().findElement(By.id("form:resetButtonId"));
        button.click();
        waitForPageToLoad();
    }

    public Integer getCounterValue(){
        WebElement text = getDriver().findElement(By.id("form:counterTextId"));
        String value = text.getText();
        try{
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }
}
