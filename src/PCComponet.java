import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

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
                cajaBusqueda.sendKeys("Mobil"+ Keys.ENTER);
                //cajaBusqueda.sendKeys(Keys.ENTER);
      /*   WebDriverWait waiting ;
         waiting= new WebDriverWait(driver, 10);
         waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.acc-block-title:nth-child(9) > a:nth-child(1)")));

        WebElement openFiltroSmartPhone =
                driver.findElement(By.cssSelector("div.acc-block-title:nth-child(9) > a:nth-child(1)"));
        openFiltroSmartPhone.click();

         WebElement selectFiltroSmartPhone =
                 driver.findElement(By.cssSelector("#acc-fil-538 > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
         selectFiltroSmartPhone.click();

         //Esto viene de starckOverflow para realizar la apertura de la parte de la izquierda, esta sin completar
         WebDriverWait wait = new WebDriverWait(driver, 10);

//Find frame or iframe and switch
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-applicationname_ModalDialog_0"));

//Now find the element
         WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@class = 'middle' and contains(text(), 'Next')]")));

//Now click using JavascriptExecutor
         ((JavascriptExecutor)driver).executeScript("arguments[0].click()" + el);

//Once all your stuff done with this frame need to switch back to default
         driver.switchTo().defaultContent();
*/
         List<WebElement> listaElementos =
                 driver.findElements(By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
         System.out.println("Numero de elementos de la lista: " + listaElementos.size());

         WebElement masElementos =
                 driver.findElement(By.xpath("//*[@id=\"btnMore\"]"));
         masElementos.click();

         //Hacer algun tipo de wait
         List<WebElement> nuevaLista =
                 driver.findElements(By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
         listaElementos.addAll(nuevaLista);

         System.out.println(listaElementos.size());

           // Hay que buscar la forma de cerrar las cookies y la pestaña de suscripcion que aparece y la de blockear notificaciones

     }

}
