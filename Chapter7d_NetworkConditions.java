import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class Chapter7d_NetworkConditions {
    ChromeDriver driver;
    DevTools devTools;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        devTools = driver.getDevTools();
    }
    @Test
    public void enableSlowRexJonesII(){
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                150,
                2500,
                2000,
                Optional.of(ConnectionType.CELLULAR3G)));
        driver.get("https://rexjones2.com");
        System.out.println("Enable Slow Network: " + driver.getTitle());
    }
    @Test
    public void doNotEnableRexJonesII(){
        driver.get("https://RexJones2.com");
        System.out.println("Do Not Enable Network: " + driver.getTitle());
    }

}
