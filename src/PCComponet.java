import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PCComponet {

     public static void main(String[] args) {
        Chrome();
    }

    private static WebDriver driver= null;


     public static void Chrome(){
         String exePath = "chromedriver/chromedriver.exe";
         System.setProperty("webdriver.chrome.driver", exePath);
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized");
         driver = new ChromeDriver(options);
         driver.get("http://www.pccomponentes.com");

         /*WebElement ventanaCookies = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/button"));
         if(ventanaCookies != null){
             ventanaCookies.click();
         }*/

         WebElement cajaBusqueda = driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div/div[2]/div/form/input"));
         //el sendKey va a venir del parametro de la interfaz en el imput
                cajaBusqueda.sendKeys("Mobil");
                cajaBusqueda.sendKeys(Keys.ENTER);
         WebDriverWait waiting ;
         waiting= new WebDriverWait(driver, 10);
         waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='acc-open-close pull-xs-right']")));

        WebElement openFiltroSmartPhone =
                driver.findElement(By.cssSelector("[class='acc-open-close pull-xs-right']"));
        openFiltroSmartPhone.click();

         WebElement selectFiltroSmartPhone =
                 driver.findElement(By.cssSelector("[data-id='1116']"));
         selectFiltroSmartPhone.click();

         List<WebElement> listaElementos =
                 driver.findElements(By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
         System.out.println("Numero de elementos de la lista: " + listaElementos.size());


     }

}
