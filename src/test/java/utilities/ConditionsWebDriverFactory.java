package utilities;

import org.testng.annotations.*;
import test.webtestsbase.Drivers;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * This class runs browser and shuts it down after each test
 */

@Listeners({ScreenShotOnFailListener.class})

public class ConditionsWebDriverFactory {

    @BeforeClass
    public void beforeTest() {

        Drivers.startBrowser(true);
        Drivers.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Drivers.getDriver().manage().window().fullscreen();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        Log.startLog(method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method) {
        Log.endLog(method.getName());
    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {

       Drivers.finishBrowser();

    }
}
