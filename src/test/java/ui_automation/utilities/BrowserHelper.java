package ui_automation.utilities;

import com.google.common.base.Function;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.LinkedList;

public class BrowserHelper {
    private static final Logger oLog = LogManager.getLogger(BrowserHelper.class);

    public BrowserHelper() {
        oLog.debug("BrowserHelper : " + Driver.getDriver().hashCode());
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<String>(
                Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windowsId.get(0));
        oLog.info("");
    }

    public void switchToParentWithChildClose() {
        switchToParentWindow();

        LinkedList<String> windowsId = new LinkedList<String>(
                Driver.getDriver().getWindowHandles());

        for (int i = 1; i < windowsId.size(); i++) {
            oLog.info(windowsId.get(i));
            Driver.getDriver().switchTo().window(windowsId.get(i));
            Driver.getDriver().close();
        }

        switchToParentWindow();
    }

    public void switchToFrame(By locator) {
        Driver.getDriver().switchTo().frame(Driver.getDriver().findElement(locator));
        oLog.info(locator);
    }

    public void switchToFrame(String nameOrId) {
        Driver.getDriver().switchTo().frame(nameOrId);
        oLog.info(nameOrId);
    }

}
