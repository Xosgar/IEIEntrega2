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
        Chrome("Iphone");
    }

    private static WebDriver driver= null;


     public static void Chrome(String nombre){
         String exePath = "chromedriver/chromedriver.exe";
         System.setProperty("webdriver.chrome.driver", exePath);
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized");
         driver = new ChromeDriver(options);
         driver.get("http://www.pccomponentes.com");


        /* WebDriverWait waiting = new WebDriverWait(driver, 10);
         waiting.until( ExpectedConditions
                 .presenceOfElementLocated( By.className("notification-cookies")));

         WebElement ventanaCookies = driver.findElement(By.className("notification-cookies"));
         ventanaCookies.findElement(By.className("btn btn-block btn-primary  btn-lg m-t-1 accept-cookie")).click();
*/
         WebElement cajaBusqueda = driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div/div[2]/div/form/input"));
         //el sendKey va a venir del parametro de la interfaz en el imput
                cajaBusqueda.sendKeys(nombre+ Keys.ENTER);
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

     // /html/body/div/a/svg
         List<WebElement> listaElementos =
                 driver.findElements(By.xpath("//*[contains(@class, 'col-xs-6 col-sm-4 col-md-4 col-lg-4')]"));
         System.out.println("Numero de elementos de la lista: " + listaElementos.size());

       /*  WebElement masElementos =
                 driver.findElement(By.xpath("//*[@id=\"btnMore\"]"));
         masElementos.click();

         //Hacer algun tipo de wait
         List<WebElement> nuevaLista =
                 driver.findElements(By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
         listaElementos.addAll(nuevaLista);

         System.out.println(listaElementos.size());
*/
           // Hay que buscar la forma de cerrar las cookies y la pestaña de suscripcion que aparece y la de blockear notificaciones
         WebElement elementoActual, nombreProducto, precio,descuento,imagen,categoria;
         int j=1;
         for (int i=0; i<listaElementos.size(); i++)
         {
             elementoActual = listaElementos.get(i);
             nombreProducto =
                     elementoActual.
                 findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                 + "]/article/div[1]/header/h3/a"));
             System.out.println(j + " " + nombreProducto.getText());
             precio = elementoActual.
                     findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                 + "]/article/div[1]/div[2]/div[1]"));
             System.out.println(j + " Precio Actual: " + precio.getText());
             try {
                 descuento = elementoActual.
                         findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                 +"]/article/div[1]/div[2]/div[2]/div[1]"));
                 if (descuento!= null) {
                     System.out.println(j + " PrecioAntes del descuento: " + descuento.getText());
                 }

             }catch (Exception err){
                 System.out.println("No tiene descuento");
             }
             imagen = elementoActual
                     .findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/div[1]/img"));
             System.out.println(j+"Url Imagen: "+imagen.getAttribute("src"));

             categoria = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article")) ;
             System.out.println(j+" Categoria: "+ categoria.getAttribute("data-category"));
             j++;
         }
     }

}
