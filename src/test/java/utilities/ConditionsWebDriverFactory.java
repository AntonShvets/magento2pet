package utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import test.webtestsbase.Drivers;

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
        //Drivers.getDriver().manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {
       Drivers.finishBrowser();

    }
}
