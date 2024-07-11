package Automationtesting.Ecommerceapp;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productsname ="ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinit\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		Landingpage landingpage = new Landingpage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("vinit12@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Vinit@123");       
        driver.findElement(By.name("login")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product-> 
        product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        prod.findElement(By.cssSelector("button[class=\"btn w-10 rounded\"]")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("button[routerlink=\"/dashboard/cart\"]")).click();
        
       List<WebElement> cartprod= driver.findElements(By.cssSelector(".cartSection h3"));
       boolean check = cartprod.stream().anyMatch(cp-> cp.getText().equalsIgnoreCase(productsname));
         Assert.assertTrue(check);
         
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
        
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
       
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a"))).click();
       //driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")).click();
       
       String str= driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertEquals(str, "THANKYOU FOR THE ORDER.");
       driver.close();
       
       //hello check git status
       
         
        
	}

}
