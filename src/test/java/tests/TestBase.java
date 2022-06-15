package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    // Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    /*
    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test" + m.getName() + "with parametrs" + Arrays.asList(p));
    }

    @AfterMethod
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }
     */
}
