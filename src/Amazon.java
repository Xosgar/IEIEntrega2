import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
//

        WebElement cajaBusqueda = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        //el sendKey va a venir del parametro de la interfaz en el imput
        cajaBusqueda.sendKeys("Mobil"+ Keys.ENTER);
        //hay que tomar bien los elementos que no esta correcto
        List<WebElement> listaElementos =
                driver.findElements(By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
        System.out.println("Numero de elementos de la lista: " + listaElementos.size());

        //hace falta añadir el filtro por marca e tipo de articulo y obtener los datos


    }
}
