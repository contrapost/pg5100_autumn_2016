package org.pg5100.exam.frontend.po;

import org.openqa.selenium.WebDriver;

public class UserDetailsPageObject extends PageObject {


    public UserDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return driver.getTitle().contains("User details");
    }

    public String getUserId(){
        return getText("userId");
    }

    public int getKarma(){
        return Integer.parseInt(getText("karma"));
    }
}
