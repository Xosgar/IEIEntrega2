import modelos.Movil;
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
         //Add chrome switch to disable notification - "**--disable-notifications**"
         options.addArguments("--disable-notifications");
         driver = new ChromeDriver(options);
         driver.get("http://www.pccomponentes.com");
         waitForPageLoaded();



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
         WebElement elementoActual, nombreProducto, precio,descuento,imagen,categoria,marca;
         String nombreP,precioP,descuentoP,imagenP,categoriaP,marcaP;
         int j=1;
         for (int i=0; i<listaElementos.size(); i++)
         {
             elementoActual = listaElementos.get(i);
             try {
                 descuento = elementoActual.
                         findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                 +"]/article/div[1]/div[2]/div[2]/div[1]"));
                 descuentoP = descuento.getText();
                 nombreProducto =
                         elementoActual.
                                 findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                         + "]/article/div[1]/header/h3/a"));
                 nombreP= nombreProducto.getText();
                 precio = elementoActual.
                         findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                 + "]/article/div[1]/div[2]/div[1]"));
                 precioP = precio.getText();

                 imagen = elementoActual
                         .findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/div[1]/img"));
                 imagenP = imagen.getAttribute("src");

                 categoria = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article")) ;
                 categoriaP= categoria.getAttribute("data-category");

                 marca = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/a"));
                 marcaP = marca.getAttribute("data-brand");

                 Movil movilActual = new Movil(nombreP,precioP,descuentoP,imagenP,marcaP);

                 System.out.println(movilActual.toString());
             }catch (Exception err){
                 nombreProducto =
                         elementoActual.
                                 findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                         + "]/article/div[1]/header/h3/a"));
                 nombreP= nombreProducto.getText();
                 precio = elementoActual.
                         findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+ j
                                 + "]/article/div[1]/div[2]/div[1]"));
                 precioP = precio.getText();

                 imagen = elementoActual
                         .findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/div[1]/img"));
                 imagenP = imagen.getAttribute("src");

                 categoria = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article")) ;
                 categoriaP= categoria.getAttribute("data-category");

                 marca = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/a"));
                 marcaP = marca.getAttribute("data-brand");

                 Movil movilActual = new Movil(nombreP,precioP,imagenP,marcaP);

                 System.out.println(movilActual.toString());
             }





             j++;
         }


           // Hay que buscar la forma de cerrar las cookies y la pestaña de suscripcion que aparece

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
