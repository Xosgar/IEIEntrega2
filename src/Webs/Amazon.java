package Webs;

import modelos.Movil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Amazon {

    public static void main(String[] args ){
        Chrome("Apple", "iPhone");
    }
    private static WebDriver driver= null;

    public static List<Movil> Chrome(String marca, String modelo){
        List<Movil> res = new ArrayList<Movil>();
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
        cajaBusqueda.sendKeys(modelo + Keys.ENTER);
        waitForPageLoaded();

        WebElement departamento = driver.findElement(By.xpath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Móviles y smartphones libres')]"));
        departamento.click();
        waitForPageLoaded();

        WebElement filtroMarca = driver.findElement(By.xpath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'" + marca + "')]"));
        filtroMarca.click();
        waitForPageLoaded();

        WebElement tiendaAmazon = driver.findElement(By.xpath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Amazon.es')]"));
        tiendaAmazon.click();
        waitForPageLoaded();

        //<span class="a-size-base a-color-base">Móviles y smartphones libres</span>
        List<WebElement> listaElementos =
                driver.findElements(By.xpath("//*[contains(@class, 'sg-col-20-of-24 s-result-item sg-col-0-of-12 sg-col-28-of-32 sg-col-16-of-20 sg-col sg-col-32-of-36 sg-col-12-of-16 sg-col-24-of-28')]"));
        System.out.println("Numero de elementos de la lista: " + listaElementos.size());

        //hace falta añadir el filtro por marca e tipo de articulo y obtener los datos

        WebElement elementoActual, nombreProducto, precio,descuento,imagen/*,categoria, marca*/;
        String nombreP,precioP,descuentoP,imagenP,categoriaP,marcaP;
        int j=1;
        for (int i=0; i<listaElementos.size(); i++)
        {
            elementoActual = listaElementos.get(i);
            try{
                nombreProducto = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div["
                                        + j + "]/div/span/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
                nombreP= nombreProducto.getText();
                try {
                    descuento = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div["
                            + j + "]/div/span/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span[2]/span[2]"));
                    descuentoP = descuento.getText();
                    precio = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div["
                            + j + "]/div/span/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span[1]/span[2]/span[1]"));
                    precioP = precio.getText();

                    imagen = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
                    imagenP = imagen.getAttribute("src");

                    Movil movilActual = new Movil(nombreP,precioP,descuentoP,imagenP,marca);
                    res.add(movilActual);

                    System.out.println(movilActual.toString());
                }catch(Exception patrocinadoSinDescuento) {
                    precio = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div["
                        + j + "]/div/span/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span[1]/span[2]/span[1]"));
                    precioP = precio.getText();

                    imagen = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
                    imagenP = imagen.getAttribute("src");

                    Movil movilActual = new Movil(nombreP,precioP,imagenP, marca);
                    res.add(movilActual);
                    System.out.println(movilActual.toString());}

            }catch (Exception noPatrocinado){
                nombreProducto = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                                    j + "]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
                nombreP= nombreProducto.getText();
                //System.out.println("nombre normal bien");
                try{
                    descuento = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span[2]/span[2]"));
                    descuentoP = descuento.getText();
                    //System.out.println("descuento normal bien");
                    precio = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span/span[2]/span[1]"));
                    precioP = precio.getText();
                    //System.out.println("precio normal bien");
                    imagen = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div[2]/div[1]/div/div/span/a/div/img"));
                    imagenP = imagen.getAttribute("src");
                    //System.out.println("imagen normal bien");

                /*categoria = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article")) ;
                categoriaP= categoria.getAttribute("data-category");*/

                /*marca = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/a"));
                marcaP = marca.getAttribute("data-brand");*/

                    Movil movilActual = new Movil(nombreP,precioP,descuentoP,imagenP, marca);
                    res.add(movilActual);
                    System.out.println(movilActual.toString());
                }catch(Exception noPatrocinadoSinDescuento) {
                    //COPIAR LO DE ARRIBA EXCEPTO DESCUENTO, Y QUITANDO descuentoP
                    precio = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div["
                            + j + "]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span/span[2]/span[1]"));
                    precioP = precio.getText();
                    //System.out.println("precio sin descuento normal bien");
                    imagen = elementoActual.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[" +
                            j + "]/div/span/div/div/div[2]/div[1]/div/div/span/a/div/img"));
                    imagenP = imagen.getAttribute("src");
                    //System.out.println("imagen sin descuento normal bien");
                /*categoria = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article")) ;
                categoriaP= categoria.getAttribute("data-category");*/

                /*marca = elementoActual.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[1]/div["+j+"]/article/div[1]/a"));
                marcaP = marca.getAttribute("data-brand");*/

                    Movil movilActual = new Movil(nombreP,precioP,imagenP, marca);
                    res.add(movilActual);
                    System.out.println(movilActual.toString());
                }

            }

            j++;
        }
        return res;
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
