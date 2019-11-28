import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Amazon {

    public static void main(String[] args ){
        Chrome();
    }
    private static WebDriver driver= null;

    public static void Chrome(){
        String exePath = "chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        //a VECES SALE AMAZON captchat y no se puede avanzar
        driver.get("https://www.amazon.es/");

        //MÉTODO PROVISIONAL PARA COMPLETAR CAPTCHA A MANO Y CONTINUAR EJECUTANDO CON ÉXITO
        WebDriverWait waiting ;
        waiting= new WebDriverWait(driver, 20);
        waiting.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-logo\"]")));


        Select selectCategoria = new Select(driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]")));
        selectCategoria.selectByVisibleText("Electrónica");

        WebElement cajaBusqueda = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        //el sendKey va a venir del parametro de la interfaz en el imput
        cajaBusqueda.sendKeys("Mobil"+ Keys.ENTER);
        waitForPageLoaded();

        WebElement departamento = driver.findElement(By.xpath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Móviles y smartphones libres')]"));
        departamento.click();
//<span class="a-size-base a-color-base">Móviles y smartphones libres</span>
        List<WebElement> listaElementos =
                driver.findElements(By.xpath("//*[contains(@class, 'sg-col-20-of-24 s-result-item sg-col-0-of-12 sg-col-28-of-32 sg-col-16-of-20 sg-col sg-col-32-of-36 sg-col-12-of-16 sg-col-24-of-28')]"));
        System.out.println("Numero de elementos de la lista: " + listaElementos.size());

        //hace falta añadir el filtro por marca e tipo de articulo y obtener los datos


    }

    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }
}
